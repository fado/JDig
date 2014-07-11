package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import main.Cell;
import main.Level;

public class LevelPanel extends JPanel {
    
    private AttributesPanel attributesPanel;
    
    public LevelPanel(Level level, MapToolbar toolbar, AttributesPanel attributesPanel) {
        this.attributesPanel = attributesPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        for(Cell cell : level.getAllCells()) {
            constraints.gridx = cell.X;
            constraints.gridy = cell.Y;
            CellPanel cellUi = new CellPanel(cell);
            add(cellUi, constraints);
            toolbar.addToolListener(cellUi);
        }
    }
    
    public AttributesPanel getAttributesPanel() {
        return this.attributesPanel;
    }
    
}
