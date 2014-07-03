package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import main.Cell;
import main.Entity;

public class ExitTool implements Tool {
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        if(!cell.isFilled()) {
            Entity potentialEntity = cell.getPotentialEntity();
            if(!potentialEntity.equals(Entity.NO_ENTITY)) {
                cellPanel.addImage(potentialEntity.getPath());
                if(potentialEntity.equals(Entity.VERTICAL_EXIT)) {
                    cellPanel.setVerticalExitBorder();
                } else if(potentialEntity.equals(Entity.HORIZONTAL_EXIT)) {
                    cellPanel.setHorizontalExitBorder();
                }
            }
        }
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        if(!cell.isFilled()) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
 
    }
}
