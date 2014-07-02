package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import main.Cell;
import main.Entity;

public class RoomTool implements Tool {

    @Override
    public void mouseEntered(CellPanel cellPanel, Cell cell, MouseEvent event) {
        if (!cell.isFilled()) {
            cellPanel.addImage(Entity.ROOM.getPath());
            cellPanel.removeBorder();
        }
    }

    @Override
    public void mouseExited(CellPanel cellPanel, Cell cell, MouseEvent event) {
        if (!cell.isFilled()) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(CellPanel cellPanel, Cell cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            cell.setEntity(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cell.setEntity(Entity.ROOM);
        }
    }
}
