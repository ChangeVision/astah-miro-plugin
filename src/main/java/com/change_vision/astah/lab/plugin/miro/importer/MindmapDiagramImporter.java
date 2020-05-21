package com.change_vision.astah.lab.plugin.miro.importer;

import com.change_vision.astah.lab.plugin.miro.MiroClient;
import com.change_vision.astah.lab.plugin.miro.widget.Collection;
import com.change_vision.astah.lab.plugin.miro.widget.Line;
import com.change_vision.astah.lab.plugin.miro.widget.Text;
import com.change_vision.jude.api.inf.editor.ITransactionManager;
import com.change_vision.jude.api.inf.editor.MindmapEditor;
import com.change_vision.jude.api.inf.exception.BadTransactionException;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IMindMapDiagram;
import com.change_vision.jude.api.inf.presentation.INodePresentation;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class MindmapDiagramImporter {
    class Topic {
        Text current;
        Topic parent;
        List<Topic> children;

        Topic(Text text) {
            current = text;
            parent = null;
            children = new ArrayList<>();
        }

        float getCurrentPositionX() {
            return this.current.getX();
        }

        float getCurrentPositionY() {
            return this.current.getY();
        }

        void print() {
            print(0);
        }

        void print(int indent) {
            for (int i = 0; i < indent; i++) System.out.print(" ");
            System.out.println("|-" + current.getName());
            children.forEach(child -> child.print(indent + 4));
        }
    }

    private MiroClient client;
    private String boardId;

    public MindmapDiagramImporter(String boardId) {
        this.client = new MiroClient();
        this.boardId = boardId;
    }

    private void importMindmapDiagram(final MindmapEditor editor) {

        try {

            final Collection textCollection = client.findMiroWidgets(this.boardId, "text");
            final Collection lineCollection = client.findMiroWidgets(this.boardId, "line");

            final Map<String, Topic> topicsMap = textCollection.getData().stream()
                    .map(Text.class::cast)
                    .filter(Text::isMindmap)
                    .collect(Collectors.toMap(Text::getId, text -> new Topic(text)));

            lineCollection.getData().stream()
                    .map(Line.class::cast)
                    .filter(Line::isMindmap)
                    .forEach(line -> {
                        final Topic parent = topicsMap.get(line.getStartWidget().getId());
                        final Topic child = topicsMap.get(line.getEndWidget().getId());
                        parent.children.add(child);
                        child.parent = parent;
                    });

            final List<Topic> sortedTopics = topicsMap.values().stream()
                    .sorted(Comparator.comparing(Topic::getCurrentPositionY))
                    .collect(Collectors.toList());

            final Topic root = sortedTopics.stream()
                    .filter(topic -> topic.parent == null)
                    .findFirst().get();

            root.print();

            final Point2D rootPosition = new Point2D.Float(root.current.getX(), root.current.getY());
            final IMindMapDiagram iMindMapDiagram = (IMindMapDiagram) editor.getDiagram();
            final INodePresentation rootTopic = iMindMapDiagram.getRoot();
            rootTopic.setLabel(root.current.getName());

            List<Topic> rightMainTopics = new ArrayList<>();
            List<Topic> leftMainTopics = new ArrayList<>();

            for (Topic child : sortedTopics) {
                if (child.parent == root) {
                    if (child.current.getX() > rootPosition.getX()) {
                        rightMainTopics.add(child);
                    } else {
                        leftMainTopics.add(child);
                    }
                }
            }

            for (Topic rightMainTopic : rightMainTopics) {
                createAstahTopic(editor, rootTopic, rightMainTopic, "right");
            }
            for (Topic leftMainTopic : leftMainTopics) {
                createAstahTopic(editor, rootTopic, leftMainTopic, "left");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidEditingException e) {
            e.printStackTrace();
        }

    }

    private void createAstahTopic(final MindmapEditor editor, final INodePresentation parent, final Topic topic, String position ) {
        try {
            INodePresentation astahTopic = editor.createTopic(parent, topic.current.getName(), position);
            if (topic.children != null) {
                topic.children.sort(Comparator.comparing(Topic::getCurrentPositionY));
                for (Topic child : topic.children) {
                    createAstahTopic(editor, astahTopic, child, position);
                }
            }
        } catch (InvalidEditingException e) {
            e.printStackTrace();
        }

    }

    public void action(final ProjectAccessor projectAccessor, final IMindMapDiagram diagram) throws InvalidEditingException, InvalidUsingException {

        final MindmapEditor editor = projectAccessor.getDiagramEditorFactory().getMindmapEditor();
        final ITransactionManager transactionManager = projectAccessor.getTransactionManager();

        editor.setDiagram(diagram);
        client.auth();

        try {
            transactionManager.beginTransaction();
            importMindmapDiagram(editor);
            transactionManager.endTransaction();
        } catch (BadTransactionException e) {
            transactionManager.abortTransaction();
        }
    }

}
