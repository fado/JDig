package gui.tools;

import globals.Entity;
import gui.MapSquare;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class RoomTool implements Tool {

    @Override
    public void mouseEntered(MapSquare square, MouseEvent event) {
        try {
            square.addImage(Entity.ROOM.getImagePath());
        } catch (IOException ex) {
            // TO-DO: Something.
        }
    }
    
    @Override
    public void mouseExited(MapSquare square, MouseEvent event) {
        square.removeImage();
    }

    @Override
    public void mousePressed(MapSquare square, MouseEvent event) {
        if(SwingUtilities.isRightMouseButton(event)) {
            square.removeEntity();
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            square.addEntity(Entity.ROOM);
        }
    }
}
