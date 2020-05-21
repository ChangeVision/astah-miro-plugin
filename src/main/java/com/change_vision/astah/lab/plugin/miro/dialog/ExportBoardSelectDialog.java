package com.change_vision.astah.lab.plugin.miro.dialog;

import com.change_vision.astah.lab.plugin.miro.MiroClient;
import com.change_vision.astah.lab.plugin.miro.exporter.ClassDiagramExporter;
import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.model.IClassDiagram;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.change_vision.jude.api.inf.ui.IPluginActionDelegate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.Map;

public class ExportBoardSelectDialog extends JDialog {
    private final JFrame window;
    private final MiroClient client;
    private JComboBox<String> boardListCombobox;

    public ExportBoardSelectDialog(JFrame window) {
        super(window);
        this.window = window;
        this.client = new MiroClient();
        setName(DialogMessages.getMessage("exportDialog.title"));
        setTitle(DialogMessages.getMessage("exportDialog.title"));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(createPanel(), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(window);
    }

    private JPanel createPanel() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JLabel label = new JLabel(DialogMessages.getMessage("exportDialog.label"));
        panel.add(label, BorderLayout.NORTH);

        String teamId = DialogUtils.getTeamId();
        Map<String, String> boardMap = DialogUtils.getBoardMap(teamId);
        boardListCombobox = createBoardListComboBox(boardMap);
        panel.add(boardListCombobox, BorderLayout.WEST);
        panel.add(createConfirmButton(e -> {
            try {
                execute(boardMap);
            } catch (IPluginActionDelegate.UnExpectedException ex) {
                ex.printStackTrace();
            }
        }), BorderLayout.CENTER);
        return panel;
    }

    private JComboBox<String> createBoardListComboBox(Map boardMap) {
        JComboBox<String> jComboBox = new JComboBox<>();
        for (Object board : boardMap.keySet()) {
            jComboBox.addItem(board.toString());
        }

        return jComboBox;
    }

    private JButton createConfirmButton(ActionListener listener) {
        final JButton confirmButton = new JButton();
        confirmButton.setText(DialogMessages.getMessage("exportDialog.exportButton"));
        confirmButton.addActionListener(listener);
        return confirmButton;
    }


    private void execute(Map boardMap) throws IPluginActionDelegate.UnExpectedException {
        final String boardName = (String) boardListCombobox.getSelectedItem();
        final String boardId = boardMap.get(boardName).toString();
        try {
            final AstahAPI api = AstahAPI.getAstahAPI();
            final ProjectAccessor projectAccessor = api.getProjectAccessor();
            final IDiagram currentDiagram = projectAccessor.getViewManager().getDiagramViewManager().getCurrentDiagram();
            final ClassDiagramExporter classDiagramExporter = new ClassDiagramExporter(boardId);
            classDiagramExporter.action((IClassDiagram) currentDiagram);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(window.getParent(),
                    DialogMessages.getMessage("commonWarning.unexpectedError"),
                    "Alert",
                    JOptionPane.ERROR_MESSAGE);
            throw new IPluginActionDelegate.UnExpectedException();
        }
    }
}
