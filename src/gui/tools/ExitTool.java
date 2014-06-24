package gui.tools;

import globals.Entity;
import gui.MapGrid;
import gui.MapSquare;
import java.io.IOException;

public class ExitTool implements Tool {
    
    /**
     * This method looks at squares adjacent to the square entered and determines
     * whether or not rooms exist there.  If rooms do exist, the cursor is changed
     * to an exit type appropriate for the rooms that may be connected by the 
     * placement of an exit.
     * @param square - The square entered by the mouse.
     */
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
        
        // Get MapSquares adjacent diagonally southwest/northeast.
        MapSquare southwestSquare = grid.getSquareSouthwestOf(square.getPosition());
        MapSquare northeastSquare = grid.getSquareNortheastOf(square.getPosition());
        // Get MapSquares adjadcent diagonally northwest/southeast.
        MapSquare northwestSquare = grid.getSquareNorthwestOf(square.getPosition());
        MapSquare southeastSquare = grid.getSquareSoutheastOf(square.getPosition());

        // Check for rooms and null points on southwest/northeast and northwest/southeast axis.
        if (southwestSquare != null && southwestSquare.hasEntity(Entity.ROOM)
                && northeastSquare != null && northeastSquare.hasEntity(Entity.ROOM)
                && northwestSquare != null && northwestSquare.hasEntity(Entity.ROOM)
                && southeastSquare != null && southeastSquare.hasEntity(Entity.ROOM)) {
            try {
                square.addImage(Entity.X_EXIT.getImagePath());
            } catch (IOException ex) {
                // TO-DO: Something.
            }
            // Check for rooms and null pointers on the southwest/northeast axis.
        } else if (southwestSquare != null && southwestSquare.hasEntity(Entity.ROOM)
                && northeastSquare != null && northeastSquare.hasEntity(Entity.ROOM)) {
            try {
                square.addImage(Entity.FORWARD_DIAGONAL_EXIT.getImagePath());

            } catch (IOException ex) {
                // TO-DO: Something.
            }
            // Check for rooms and null pointers on the northwest/southeast axis.
        } else if (northwestSquare != null && northwestSquare.hasEntity(Entity.ROOM)
                && southeastSquare != null && southeastSquare.hasEntity(Entity.ROOM)) {
            try {
                square.addImage(Entity.BACK_DIAGONAL_EXIT.getImagePath());
            } catch (IOException ex) {
                // TO-DO: Something.
            }
        }
    }

    /**
     * Removes the image from the square upon the mouse exiting.
     * @param square - The square exited by the mouse.
     */
    @Override
    public void mouseExited(MapSquare square) {
        square.removeImage();
    }

    @Override
    public void mouseClicked(MapSquare square) {
        
    }
  
}