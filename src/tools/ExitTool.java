package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import main.Cell;
import main.Entity;

public class ExitTool implements Tool {
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        Entity potentialEntity = cell.getPotentialEntity();
        
        if(!cell.isFilled()) {    
            if(!potentialEntity.equals(Entity.NO_ENTITY)) {
                cellPanel.addImage(potentialEntity.getPath());
                setBorder(cellPanel, potentialEntity);
            }
        }
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
 
    }
 
    private void setBorder(CellPanel cellPanel, Entity potentialEntity) {
        if (potentialEntity.equals(Entity.VERTICAL_EXIT)) {
            cellPanel.setVerticalExitBorder();
        } else if (potentialEntity.equals(Entity.HORIZONTAL_EXIT)) {
            cellPanel.setHorizontalExitBorder();
        }
    }
    
}
