package gui.tools;

import globals.Entity;
import gui.MapGrid;
import gui.MapSquare;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ExitTool implements Tool {
    
    /**
     * This method looks at squares adjacent to the square entered and determines
     * whether or not rooms exist there.  If rooms do exist, the cursor is changed
     * to an exit type appropriate for the rooms that may be connected by the 
     * placement of an exit.
     * @param square - The square entered by the mouse.
     * @param event - The MouseEvent that triggered the method call.
     */
    @Override
    public void mouseEntered(MapSquare square, MouseEvent event) {
        
        MapGrid grid = square.getMapGrid();

        // Get MapSquares adjacent horizontally.
        MapSquare westSquare = grid.getSquareWestOf(square.getPosition());
        MapSquare eastSquare = grid.getSquareEastOf(square.getPosition());
        // Check for rooms and null pointers.
        if (westSquare != null && westSquare.containsEntity(Entity.ROOM) &&
                eastSquare != null && eastSquare.containsEntity(Entity.ROOM)) {
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
        if (northSquare != null && northSquare.containsEntity(Entity.ROOM) &&
                southSquare != null && southSquare.containsEntity(Entity.ROOM)) {
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
        if (southwestSquare != null && southwestSquare.containsEntity(Entity.ROOM)
                && northeastSquare != null && northeastSquare.containsEntity(Entity.ROOM)
                && northwestSquare != null && northwestSquare.containsEntity(Entity.ROOM)
                && southeastSquare != null && southeastSquare.containsEntity(Entity.ROOM)) {
            try {
                square.addImage(Entity.X_EXIT.getImagePath());
            } catch (IOException ex) {
                // TO-DO: Something.
            }
            // Check for rooms and null pointers on the southwest/northeast axis.
        } else if (southwestSquare != null && southwestSquare.containsEntity(Entity.ROOM)
                && northeastSquare != null && northeastSquare.containsEntity(Entity.ROOM)) {
            try {
                square.addImage(Entity.FORWARD_DIAGONAL_EXIT.getImagePath());

            } catch (IOException ex) {
                // TO-DO: Something.
            }
            // Check for rooms and null pointers on the northwest/southeast axis.
        } else if (northwestSquare != null && northwestSquare.containsEntity(Entity.ROOM)
                && southeastSquare != null && southeastSquare.containsEntity(Entity.ROOM)) {
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
     * @param event - The MouseEvent that triggered the method call.
     */
    @Override
    public void mouseExited(MapSquare square, MouseEvent event) {
        if(!square.containsEntity(Entity.ROOM)) {
            square.removeImage();
        }
    }

    @Override
    public void mouseClicked(MapSquare square, MouseEvent event) {
        
    }
  
}