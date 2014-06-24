package gui.tools;

import globals.Entity;
import gui.MapSquare;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class RoomTool implements Tool {
    
    @Override
    public void mouseEntered(MapSquare square, MouseEvent event) {
        if (!square.containsAnyEntity()) {
            try {
                square.addImage(Entity.ROOM.getImagePath());
            } catch (IOException ex) {
                // TO-DO: Something.
            }
        }
    }
    
    @Override
    public void mouseExited(MapSquare square, MouseEvent event) {
        if(!square.containsAnyEntity()) {
            square.removeImage();
        }
    }

    @Override
    public void mouseClicked(MapSquare square, MouseEvent event) {
        if(SwingUtilities.isRightMouseButton(event)) {
            square.removeEntity();
            if(square.containsAnyEntity()) {
                System.out.println("Trace.");
            }
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            square.addEntity(Entity.ROOM);
        }
    }
}
