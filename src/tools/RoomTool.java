package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import main.Cell;
import main.Entity;
import main.Map;

public class RoomTool implements Tool {

    @Override
    public void mouseEntered(CellPanel cellPanel, MouseEvent event) {
        Cell cellObject = cellPanel.getCellObject();
        Map parentMap = cellObject.getParentMap();
        if(parentMap.getPotentialEntity(cellObject).equals(Entity.NO_ENTITY)) {
            cellPanel.addImage(Entity.ROOM.getPath());
            cellPanel.removeBorder();
        }
        
    }

    @Override
    public void mouseExited(CellPanel cellPanel, MouseEvent event) {
        Cell cellObject = cellPanel.getCellObject();
        if(!cellObject.isFilled()) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(CellPanel cellPanel, MouseEvent event) {
        Cell cellObject = cellPanel.getCellObject();
        if(SwingUtilities.isRightMouseButton(event)) {
            cellObject.setEntity(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cellObject.setEntity(Entity.ROOM);
            cellPanel.removeBorder();
        }
    }
}
