package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import main.Cell;
import main.Map;

/**
 * The MapGrid class specifies a collection of MapSquare that comprise the user
 * interface for drawing maps within JDig.
 */
public class MapPanel extends JPanel {
    
    private AttributesPanel attributesPanel;
    
    public MapPanel(Map map, MapToolbar toolbar, AttributesPanel attributesPanel) {
        this.attributesPanel = attributesPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        for(Cell cell : map.getAllCells()) {
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
