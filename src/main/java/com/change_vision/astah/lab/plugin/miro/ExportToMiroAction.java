package com.change_vision.astah.lab.plugin.miro;

import com.change_vision.astah.lab.plugin.miro.dialog.DialogMessages;
import com.change_vision.astah.lab.plugin.miro.dialog.ExportBoardSelectDialog;
import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IClassDiagram;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;
import com.change_vision.jude.api.inf.ui.IWindow;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExportToMiroAction implements IPluginActionDelegate {
    public Object run(IWindow window) throws IPluginActionDelegate.UnExpectedException {
        try {
            final AstahAPI api = AstahAPI.getAstahAPI();
            final ProjectAccessor projectAccessor = api.getProjectAccessor();

            if (!projectAccessor.hasProject()) {
                final String message = DialogMessages.getMessage("commonWarning.projectIsNotOpened");
                JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            final IDiagram currentDiagram = projectAccessor.getViewManager().getDiagramViewManager().getCurrentDiagram();
            if (currentDiagram == null) {
                final String message = DialogMessages.getMessage("commonWarning.diagramIsNotOpened");
                JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            if (!(currentDiagram instanceof IClassDiagram)) {
                final String message = DialogMessages.getMessage("exportDialog.warning.diagramIsNotSupported");
                JOptionPane.showMessageDialog(window.getParent(), message, "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                ExportBoardSelectDialog dialog = new ExportBoardSelectDialog((JFrame) window.getParent());
                dialog.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window.getParent(),
                    DialogMessages.getMessage("commonWarning.unexpectedError"),
                    "Alert",
                    JOptionPane.ERROR_MESSAGE);
            throw new IPluginActionDelegate.UnExpectedException();
        }
        return null;
    }

}
