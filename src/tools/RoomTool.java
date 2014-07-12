package tools;

import gui.CellView;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import main.Cell;
import main.entities.Entity;

public class RoomTool implements Tool {

    private final String ROOM_IMAGE = "./resources/images/room.png";
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellView cellPanel = (CellView)event.getSource();
        if (cell.getEntity().equals(Entity.NO_ENTITY)) {
            cellPanel.addImage(ROOM_IMAGE);
            cellPanel.removeBorder();
        }
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        CellView cellPanel = (CellView) event.getSource();
        if (cell.getEntity().equals(Entity.NO_ENTITY)) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            cell.setEntityType(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cell.setEntityType(Entity.ROOM);
            cell.setRoom();
        }
    }
}
