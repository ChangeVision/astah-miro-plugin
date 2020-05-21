package com.change_vision.astah.lab.plugin.miro.exporter;

import com.change_vision.astah.lab.plugin.miro.MiroClient;
import com.change_vision.astah.lab.plugin.miro.widget.IWidget;
import com.change_vision.astah.lab.plugin.miro.widget.Line;
import com.change_vision.astah.lab.plugin.miro.widget.Shape;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IAttribute;
import com.change_vision.jude.api.inf.model.IAssociation;
import com.change_vision.jude.api.inf.model.IClassDiagram;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IGeneralization;
import com.change_vision.jude.api.inf.model.IRealization;
import com.change_vision.jude.api.inf.model.IUsage;
import com.change_vision.jude.api.inf.presentation.ILinkPresentation;
import com.change_vision.jude.api.inf.presentation.INodePresentation;
import com.change_vision.jude.api.inf.presentation.IPresentation;

import java.util.HashMap;
import java.util.Map;

public class ClassDiagramExporter {

    private MiroClient client;
    private String boardId;

    public ClassDiagramExporter(String boardId) {
        this.client = new MiroClient();
        this.boardId = boardId;
    }

    private Shape createShape(final INodePresentation nodePresentation) {
        final Shape shape = Shape.createNewInstance();
        shape.setText(nodePresentation.getLabel());
        shape.setX((float) nodePresentation.getRectangle().getCenterX());
        shape.setY((float) nodePresentation.getRectangle().getCenterY());
        shape.setHeight((float) nodePresentation.getHeight());
        shape.setWidth((float) nodePresentation.getWidth());
        return shape;
    }

    private String convertEndTypeFromAstahToMiro(final IAttribute memberEnd) {
        if (memberEnd.isAggregate()) {
            return "rhombus";
        } else if (memberEnd.isComposite()) {
            return "opaque_rhombus";
        } else if (memberEnd.getNavigability().equals("Navigable")) {
            return "open_arrow";
        } else {
            return "none";
        }
    }

    private Line createLine(final ILinkPresentation linkPresentation, final Shape source, final Shape target) {
        final IElement model = linkPresentation.getModel();
        if (model instanceof IAssociation) {
            return Line.createAssociation(source,
                    target,
                    convertEndTypeFromAstahToMiro(
                            ((IAssociation) model).getMemberEnds()[0]),
                    convertEndTypeFromAstahToMiro(
                            ((IAssociation) model).getMemberEnds()[1]));
        } else if (model instanceof IGeneralization) {
            return Line.createGeneralization(source, target);
        } else if (model instanceof IRealization) {
            return Line.createRealization(target, source);
        } else if (model instanceof IUsage) {
            return Line.createUsage(target, source);
        } else if (model instanceof IDependency) {
            return Line.createDependency(target, source);
        }
        return new Line(source, target);
    }

    public void action(final IClassDiagram diagram) throws InvalidUsingException {

        final Map<IPresentation, IWidget> widgets = new HashMap<>();

        for (final IPresentation presentation : diagram.getPresentations()) {
            if (presentation.getType().equals("Class")) {
                final Shape transientShape = createShape((INodePresentation) presentation);
                final Shape shape = (Shape) client.createMiroWidget(boardId, transientShape);
                // for debug (TODO should be used logger)
                System.out.println("Shape Info: " + shape.toString());
                widgets.put(presentation, shape);
            }
        }
        for (final IPresentation presentation : diagram.getPresentations()) {
            if (presentation instanceof ILinkPresentation) {
                final ILinkPresentation linkPresentation = (ILinkPresentation) presentation;
                final Shape source = (Shape) widgets.get(linkPresentation.getSource());
                final Shape target = (Shape) widgets.get(linkPresentation.getTarget());
                final Line transientLine = createLine(linkPresentation, source, target);
                final Line line = (Line) client.createMiroWidget(boardId, transientLine);
                widgets.put(presentation, line);
            }
        }
        for (final IPresentation presentation : diagram.getPresentations()) {
            if (presentation instanceof ILinkPresentation) {
                final ILinkPresentation linkPresentation = (ILinkPresentation) presentation;
                final Line createdLine = (Line) widgets.get(linkPresentation);
                final Shape source = (Shape) widgets.get(linkPresentation.getSource());
                final Shape target = (Shape) widgets.get(linkPresentation.getTarget());
                final Line transientLine = createLine(linkPresentation, source, target);
                final Line line = (Line) client.updateMiroWidget(boardId, createdLine.getId(), transientLine);
                widgets.put(presentation, line);
            }
        }
    }
}
