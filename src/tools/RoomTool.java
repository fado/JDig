package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import main.Entity;

public class RoomTool implements Tool {

    @Override
    public void mouseEntered(CellPanel cellPanel, MouseEvent event) {
        if (!cellPanel.isFilled()) {
            cellPanel.addImage(Entity.ROOM.getPath());
            cellPanel.removeBorder();
        }
    }

    @Override
    public void mouseExited(CellPanel cellPanel, MouseEvent event) {
        if (!cellPanel.isFilled()) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(CellPanel cellPanel, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            cellPanel.setEntity(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cellPanel.setEntity(Entity.ROOM);
        }
    }
}
