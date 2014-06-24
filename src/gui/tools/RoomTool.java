package gui.tools;

import globals.Entity;
import gui.MapSquare;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class RoomTool implements Tool {

    private Border defaultBorder;
    
    @Override
    public void mouseEntered(MapSquare square) {
        defaultBorder = square.getBorder();
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        square.setBorder(blackBorder);
    }
    
    @Override
    public void mouseExited(MapSquare square) {
        if(!square.isFilled()) {
            square.setBorder(defaultBorder);
        }
    }

    @Override
    public void mouseClicked(MapSquare square) {
        if(!square.isFilled()) {
            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
            square.setBorder(blackBorder);
            square.setEntity(Entity.ROOM);
        }
    }

}