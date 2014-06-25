package gui.tools;

import globals.Entity;
import gui.MapSquare;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class RoomTool implements Tool {

    @Override
    public void mouseEntered(MapSquare square, MouseEvent event) {
        square.addImage(Entity.ROOM.getImagePath());
        square.removeBorder();
    }

    @Override
    public void mouseExited(MapSquare square, MouseEvent event) {
        square.removeImage();
        square.restoreDefaultBorder();
    }

    @Override
    public void mousePressed(MapSquare square, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            square.removeEntity();
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            square.addEntity(Entity.ROOM);
            square.removeBorder();
        }
    }
}
