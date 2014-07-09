package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import main.Cell;
import main.entities.EntityType;
import main.entities.ExitType;

public class ExitTool implements Tool {
    
    ExitType exitType;
    
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
            cell.setEntityType(EntityType.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cell.setFilled(true);
        }
    }
 
    private void setBorder(CellPanel cellPanel, ExitType exitType) {
        if (exitType == ExitType.VERTICAL_EXIT) {
            cellPanel.setVerticalExitBorder();
        } else if (exitType == ExitType.HORIZONTAL_EXIT) {            
            cellPanel.setHorizontalExitBorder();
        }
    }
    
}
