package com.change_vision.astah.lab.plugin.miro.importer;

import com.change_vision.astah.lab.plugin.miro.MiroClient;
import com.change_vision.astah.lab.plugin.miro.widget.Collection;
import com.change_vision.astah.lab.plugin.miro.widget.IWidget;
import com.change_vision.astah.lab.plugin.miro.widget.Line;
import com.change_vision.astah.lab.plugin.miro.widget.Shape;
import com.change_vision.jude.api.inf.editor.BasicModelEditor;
import com.change_vision.jude.api.inf.editor.ClassDiagramEditor;
import com.change_vision.jude.api.inf.editor.ITransactionManager;
import com.change_vision.jude.api.inf.exception.BadTransactionException;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IAssociation;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.model.IDependency;
import com.change_vision.jude.api.inf.model.IClassDiagram;
import com.change_vision.jude.api.inf.model.IGeneralization;
import com.change_vision.jude.api.inf.model.IRealization;
import com.change_vision.jude.api.inf.presentation.ILinkPresentation;
import com.change_vision.jude.api.inf.presentation.INodePresentation;
import com.change_vision.jude.api.inf.presentation.IPresentation;
import com.change_vision.jude.api.inf.project.ProjectAccessor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClassDiagramImporter {

    private MiroClient client;
    private String boardId;

    public ClassDiagramImporter(String boardId) {
        this.client = new MiroClient();
        this.boardId = boardId;
    }

    private IClass createAstahClass(
            final BasicModelEditor modelEditor,
            final ClassDiagramEditor diagramEditor, final Shape shape) throws InvalidEditingException {
        final IElement parent = diagramEditor.getDiagram().getContainer();
        return modelEditor.createClass((IPackage) parent, shape.getName());
    }

    private INodePresentation createAstahClassPresentation(
            final BasicModelEditor modelEditor,
            final ClassDiagramEditor diagramEditor, final Shape shape) {
        if (shape.getName().isEmpty()) {
            return null;
        }
        try {
            final IClass model = createAstahClass(modelEditor, diagramEditor, shape);
            final INodePresentation node = diagramEditor.createNodePresentation(model, shape.getLocation());
            node.setHeight(shape.getHeight());
            node.setWidth(shape.getWidth());
            return node;
        } catch (InvalidEditingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ILinkPresentation createAstahAssociationLinkPresentation(
            final BasicModelEditor modelEditor,
            final ClassDiagramEditor diagramEditor, final Line line,
            final Map<String, IPresentation> buffer) {
        try {
            final INodePresentation sourceClass =
                    (INodePresentation) buffer.get(line.getSourceClass().getId());
            final INodePresentation targetClass =
                    (INodePresentation) buffer.get(line.getTargetClass().getId());
            if (sourceClass == null || targetClass == null) return null;
            final IAssociation association = modelEditor.createAssociation(
                    (IClass) sourceClass.getModel(),
                    (IClass) targetClass.getModel(),
                    "", "", "");
            if (line.sourceEndTypeIsAggregate()) {
                association.getMemberEnds()[0].setAggregation();
            } else if (line.sourceEndTypeIsComposite()) {
                association.getMemberEnds()[0].setComposite();
            } else if (line.sourceEndTypeIsNavigable()) {
                association.getMemberEnds()[0].setNavigability("Navigable");
            }
            if (line.targetEndTypeIsAggregate()) {
                association.getMemberEnds()[1].setAggregation();
            } else if (line.targetEndTypeIsComposite()) {
                association.getMemberEnds()[1].setComposite();
            } else if (line.targetEndTypeIsNavigable()) {
                association.getMemberEnds()[1].setNavigability("Navigable");
            }
            return diagramEditor.createLinkPresentation(association, sourceClass, targetClass);
        } catch (InvalidEditingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ILinkPresentation createAstahDependencyLinkPresentation(
            final BasicModelEditor modelEditor,
            final ClassDiagramEditor diagramEditor, final Line line,
            final Map<String, IPresentation> buffer) {
        try {
            final INodePresentation clientClass =
                    (INodePresentation) buffer.get(line.getClient().getId());
            final INodePresentation supplierClass =
                    (INodePresentation) buffer.get(line.getSupplier().getId());
            if (clientClass == null || supplierClass == null) return null;
            final IDependency dependency = modelEditor.createDependency(
                    (IClass) supplierClass.getModel(),
                    (IClass) clientClass.getModel(),
                    "");
            return diagramEditor.createLinkPresentation(dependency, supplierClass, clientClass);
        } catch (InvalidEditingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ILinkPresentation createAstahGeneralizationLinkPresentation(
            final BasicModelEditor modelEditor,
            final ClassDiagramEditor diagramEditor, final Line line,
            final Map<String, IPresentation> buffer) {
        try {
            final INodePresentation superClass =
                    (INodePresentation) buffer.get(line.getSuperClass().getId());
            final INodePresentation subClass =
                    (INodePresentation) buffer.get(line.getSubClass().getId());
            if (superClass == null || subClass == null) return null;
            final IGeneralization generalization = modelEditor.createGeneralization(
                    (IClass) subClass.getModel(),
                    (IClass) superClass.getModel(),
                    "");
            return diagramEditor.createLinkPresentation(generalization, subClass, superClass);
        } catch (InvalidEditingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ILinkPresentation createAstahRealizationLinkPresentation(
            final BasicModelEditor modelEditor,
            final ClassDiagramEditor diagramEditor, final Line line,
            final Map<String, IPresentation> buffer) {
        try {
            final INodePresentation clientClass =
                    (INodePresentation) buffer.get(line.getClient().getId());
            final INodePresentation supplierClass =
                    (INodePresentation) buffer.get(line.getSupplier().getId());
            if (clientClass == null || supplierClass == null) return null;
            final IRealization realization = modelEditor.createRealization(
                    (IClass) clientClass.getModel(),
                    (IClass) supplierClass.getModel(),
                    "");
            return diagramEditor.createLinkPresentation(realization, clientClass, supplierClass);
        } catch (InvalidEditingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ILinkPresentation createAstahLinkPresentation(
            final BasicModelEditor modelEditor,
            final ClassDiagramEditor diagramEditor,
            final Line line,
            final Map<String, IPresentation> buffer) {
        if (line.isAssociation()) {
            return createAstahAssociationLinkPresentation(modelEditor, diagramEditor, line, buffer);
        } else if (line.isDependency()) {
            return createAstahDependencyLinkPresentation(modelEditor, diagramEditor, line, buffer);
        } else if (line.isGeneralization()) {
            return createAstahGeneralizationLinkPresentation(modelEditor, diagramEditor, line, buffer);
        } else if (line.isRealization()) {
            return createAstahRealizationLinkPresentation(modelEditor, diagramEditor, line, buffer);
        }
        return null;
    }

    private void importClassDiagram(final BasicModelEditor modelEditor, final ClassDiagramEditor diagramEditor) {
        final Map<String, IPresentation> buffer = new HashMap<>();
        try {
            final Collection shapeCollection = client.findMiroWidgets(this.boardId, "shape");
            final Collection lineCollection = client.findMiroWidgets(this.boardId, "line");
            for (final IWidget widget : shapeCollection.getData()) {
                final INodePresentation node = createAstahClassPresentation(modelEditor, diagramEditor, (Shape) widget);
                if (node != null)
                    buffer.put(widget.getId(), node);
            }
            for (final IWidget widget : lineCollection.getData()) {
                createAstahLinkPresentation(modelEditor, diagramEditor, (Line) widget, buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void action(final ProjectAccessor projectAccessor, final IClassDiagram diagram) throws InvalidEditingException, InvalidUsingException {

        final BasicModelEditor modelEditor = projectAccessor.getModelEditorFactory().getBasicModelEditor();
        final ClassDiagramEditor diagramEditor = projectAccessor.getDiagramEditorFactory().getClassDiagramEditor();
        final ITransactionManager transactionManager = projectAccessor.getTransactionManager();
        diagramEditor.setDiagram(diagram);

        try {
            transactionManager.beginTransaction();
            importClassDiagram(modelEditor, diagramEditor);
            transactionManager.endTransaction();
        } catch (BadTransactionException e) {
            transactionManager.abortTransaction();
        }
    }
}
