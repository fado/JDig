package gui.tools;

import gui.MapSquare;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class RoomTool implements Tool {

    private final String ROOM = "./resources/images/room.png";
    
    @Override
    public void mouseEntered(MapSquare square, MouseEvent event) {
        square.addImage(ROOM);
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
             if(square.isExit()) {
                square.setExit(false);
            } else if(square.isRoom()) {
                square.setRoom(false);
            }
            square.restoreDefaultBorder();
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            square.setRoom(true);
            square.removeBorder();
        }
    }
}
