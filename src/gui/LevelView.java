package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import main.CellModel;
import main.LevelModel;

public class LevelView extends JPanel {
    
    private AttributesPanel attributesPanel;
    
    public LevelView(LevelModel level, MapToolbar toolbar, AttributesPanel attributesPanel) {
        this.attributesPanel = attributesPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        for(CellModel cell : level.getAllCells()) {
            constraints.gridx = cell.X;
            constraints.gridy = cell.Y;
            CellView cellUi = new CellView(cell);
            add(cellUi, constraints);
            toolbar.addToolListener(cellUi);
        }
    }
    
    public AttributesPanel getAttributesPanel() {
        return this.attributesPanel;
    }
    
}
