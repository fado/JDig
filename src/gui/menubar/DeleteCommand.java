package gui.menubar;

import gui.levelpanel.CellPanel;
import tools.DeletionTool;
import tools.SelectionTool;

import java.util.List;

public class DeleteCommand extends Command {

    private SelectionTool selectionTool;

    public DeleteCommand(SelectionTool selectionTool) {
        this.selectionTool = selectionTool;
    }

    @Override
    public void execute() {
        List<CellPanel> selectedPanels = selectionTool.getSelectedPanels();
        DeletionTool deletionTool = new DeletionTool();
        for(CellPanel cellPanel : selectedPanels) {
            deletionTool.deleteEntity(cellPanel);
        }
    }
}
