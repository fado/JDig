package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import main.Cell;
import main.entities.Entity;
import main.entities.Exit;

public class ExitTool implements Tool {
    
    Exit exitType;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        exitType = cell.getPotentialExitType();
        
        if(!cell.isFilled()) {
            if(exitType != null) {
                cellPanel.addImage(exitType.getPath());
                setBorder(cellPanel, exitType);    
            }
        }
    }

    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        if (!cell.isFilled()) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            cell.setEntityType(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cell.setFilled(true);
        }
    }
 
    private void setBorder(CellPanel cellPanel, Exit exit) {
        if (exit == Exit.VERTICAL_EXIT) {
            cellPanel.setVerticalExitBorder();
        } else if (exit == Exit.HORIZONTAL_EXIT) {            
            cellPanel.setHorizontalExitBorder();
        }
    }
    
}
