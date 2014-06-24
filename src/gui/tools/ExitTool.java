package gui.tools;

import globals.Entity;
import gui.MapGrid;
import gui.MapSquare;
import java.io.IOException;

public class ExitTool implements Tool {
    
    @Override
    public void mouseEntered(MapSquare square) {
        
        MapGrid grid = square.getMapGrid();

        // Get MapSquares adjacent horizontally.
        MapSquare westSquare = grid.getSquareWestOf(square.getPosition());
        MapSquare eastSquare = grid.getSquareEastOf(square.getPosition());
        // Check for rooms and null pointers.
        if (westSquare != null && westSquare.hasEntity(Entity.ROOM) &&
                eastSquare != null && eastSquare.hasEntity(Entity.ROOM)) {
            try {
                square.setHorizontalExitBorder();
                square.addImage(Entity.HORIZONTAL_EXIT.getImagePath());
            } catch (IOException ex) {
                // TO-DO: Something.
            }
        }
        
        // Get MapSquares adjacent vertically.
        MapSquare northSquare = grid.getSquareNorthOf(square.getPosition());
        MapSquare southSquare = grid.getSquareSouthOf(square.getPosition());
        // Check for rooms and null pointers.
        if (northSquare != null && northSquare.hasEntity(Entity.ROOM) &&
                southSquare != null && southSquare.hasEntity(Entity.ROOM)) {
            try {
                square.setVerticalExitBorder();
                square.addImage(Entity.VERTICAL_EXIT.getImagePath());
            } catch (IOException ex) {
                // TO-DO: Something.
            }
        }
    }

    @Override
    public void mouseExited(MapSquare square) {
        square.restoreDefaultBorder();
        square.removeImage();
    }

    @Override
    public void mouseClicked(MapSquare square) {
        
    }
  
}