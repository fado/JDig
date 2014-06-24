package gui.tools;

import globals.Entity;
import gui.MapSquare;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class RoomTool implements Tool {

    private Border defaultBorder;
    
    @Override
    public void mouseEntered(MapSquare square) {
        try {
            //defaultBorder = square.getBorder();
            //Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            //square.setBorder(blackBorder);
            square.addImage(Entity.ROOM.getImagePath());
        } catch (IOException ex) {
            // TO-DO: Something.
        }
    }
    
    @Override
    public void mouseExited(MapSquare square) {
        if(!square.isFilled()) {
            //square.setBorder(defaultBorder);
            square.removeImage();
        }
    }

    @Override
    public void mouseClicked(MapSquare square) {
        if(!square.isFilled()) {
            try {
                //Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
                //square.setBorder(blackBorder);
                square.addImage(Entity.ROOM.getImagePath());
            } catch (IOException ex) {
                // TO-DO: Something.
            }
            square.setEntity(Entity.ROOM);
        }
    }

}