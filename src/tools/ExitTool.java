package tools;

import gui.CellView;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import main.CellModel;
import main.entities.Entity;
import main.entities.Exit;

public class ExitTool implements Tool {
    
    Exit exitType;
    
    @Override
    public void mouseEntered(CellModel cell, MouseEvent event) {
        CellView cellPanel = (CellView)event.getSource();
        exitType = cell.getPotentialExitType();
        
        if(cell.getEntity().equals(Entity.NO_ENTITY)) {
            if(exitType != null) {
                cellPanel.addImage(exitType.getPath());
                setBorder(cellPanel, exitType);    
            }
        }
    }

    @Override
    public void mouseExited(CellModel cell, MouseEvent event) {
        CellView cellPanel = (CellView)event.getSource();
        if (cell.getEntity().equals(Entity.NO_ENTITY)) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(CellModel cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            cell.setEntityType(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cell.setEntityType(Entity.EXIT);
        }
    }
 
    private void setBorder(CellView cellPanel, Exit exit) {
        if (exit == Exit.VERTICAL_EXIT) {
            cellPanel.setVerticalExitBorder();
        } else if (exit == Exit.HORIZONTAL_EXIT) {            
            cellPanel.setHorizontalExitBorder();
        }
    }
    
}
