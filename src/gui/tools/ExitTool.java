package gui.tools;

import globals.Entity;
import gui.MapGrid;
import gui.MapSquare;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class ExitTool implements Tool {

    private Entity currentEntity;

    /**
     * This method looks at squares adjacent to the square entered and
     * determines whether or not rooms exist there. If rooms do exist, the
     * cursor is changed to an exit type appropriate for the rooms that may be
     * connected by the placement of an exit.
     *
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
        if (westSquare.isInBounds() && westSquare.containsEntity(Entity.ROOM)
                && eastSquare.isInBounds() && eastSquare.containsEntity(Entity.ROOM)) {
            square.setHorizontalExitBorder();
            square.addImage(Entity.HORIZONTAL_EXIT.getImagePath());
            this.currentEntity = Entity.HORIZONTAL_EXIT;
        }

        // Get MapSquares adjacent vertically.
        MapSquare northSquare = grid.getSquareNorthOf(square.getPosition());
        MapSquare southSquare = grid.getSquareSouthOf(square.getPosition());
        // Check for rooms and null pointers.
        if (northSquare.isInBounds() && northSquare.containsEntity(Entity.ROOM)
                && southSquare.isInBounds() && southSquare.containsEntity(Entity.ROOM)) {
            square.setVerticalExitBorder();
            square.addImage(Entity.VERTICAL_EXIT.getImagePath());
            this.currentEntity = Entity.VERTICAL_EXIT;
        }

        // Get MapSquares adjacent diagonally southwest/northeast.
        MapSquare southwestSquare = grid.getSquareSouthwestOf(square.getPosition());
        MapSquare northeastSquare = grid.getSquareNortheastOf(square.getPosition());
        // Get MapSquares adjadcent diagonally northwest/southeast.
        MapSquare northwestSquare = grid.getSquareNorthwestOf(square.getPosition());
        MapSquare southeastSquare = grid.getSquareSoutheastOf(square.getPosition());

        // Check for rooms and null points on southwest/northeast and northwest/southeast axis.
        if (southwestSquare.isInBounds() && southwestSquare.containsEntity(Entity.ROOM)
                && northeastSquare.isInBounds() && northeastSquare.containsEntity(Entity.ROOM)
                && northwestSquare.isInBounds() && northwestSquare.containsEntity(Entity.ROOM)
                && southeastSquare.isInBounds() && southeastSquare.containsEntity(Entity.ROOM)) {
            square.addImage(Entity.X_EXIT.getImagePath());
            this.currentEntity = Entity.X_EXIT;
        // Check for rooms and null pointers on the southwest/northeast axis.
        } else if (southwestSquare.isInBounds() && southwestSquare.containsEntity(Entity.ROOM)
                && northeastSquare.isInBounds() && northeastSquare.containsEntity(Entity.ROOM)) {
            square.addImage(Entity.FORWARD_DIAGONAL_EXIT.getImagePath());
            this.currentEntity = Entity.FORWARD_DIAGONAL_EXIT;
        // Check for rooms and null pointers on the northwest/southeast axis.
        } else if (northwestSquare.isInBounds() && northwestSquare.containsEntity(Entity.ROOM)
                && southeastSquare.isInBounds() && southeastSquare.containsEntity(Entity.ROOM)) {
            square.addImage(Entity.BACK_DIAGONAL_EXIT.getImagePath());
            this.currentEntity = Entity.BACK_DIAGONAL_EXIT;
        }
    }

    /**
     * Removes the image from the square upon the mouse exiting.
     *
     * @param square - The square exited by the mouse.
     * @param event - The MouseEvent that triggered the method call.
     */
    @Override
    public void mouseExited(MapSquare square, MouseEvent event) {
        square.removeImage();
    }

    @Override
    public void mousePressed(MapSquare square, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            square.removeEntity();
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            square.addEntity(currentEntity);
        }
    }
}
