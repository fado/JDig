package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import main.Entity;

public class ExitTool implements Tool {
    
    @Override
    public void mouseEntered(CellPanel cellPanel, MouseEvent event) {
        if(!cellPanel.isFilled()) {
            Entity potentialEntity = cellPanel.getPotentialEntity();
            if(!potentialEntity.equals(Entity.NO_ENTITY)) {
                cellPanel.addImage(cellPanel.getPotentialEntity().getPath());
                if(potentialEntity.equals(Entity.VERTICAL_EXIT)) {
                    cellPanel.setVerticalExitBorder();
                } else if(potentialEntity.equals(Entity.HORIZONTAL_EXIT)) {
                    cellPanel.setHorizontalExitBorder();
                }
            }
        }
    }
    
    @Override
    public void mouseExited(CellPanel cellPanel, MouseEvent event) {
        if(!cellPanel.isFilled()) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(CellPanel cellPanel, MouseEvent event) {
 
    }
}
