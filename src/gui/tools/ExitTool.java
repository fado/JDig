package gui.tools;

import gui.MapGrid;
import gui.MapSquare;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

public class ExitTool implements Tool {

    private final String HORIZONTAL_EXIT = "./resources/images/horizontal_exit.png";
    private final String VERTICAL_EXIT = "./resources/images/vertical_exit.png";
    private final String FORWARD_DIAGONAL_EXIT = "./resources/images/forward_diagonal_exit.png";
    private final String BACK_DIAGONAL_EXIT = "./resources/images/back_diagonal_exit.png";
    private final String X_EXIT = "./resources/images/x_exit.png";
    
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
        if (westSquare.isInBounds() && westSquare.isRoom()
                && eastSquare.isInBounds() && eastSquare.isRoom()) {
            square.setHorizontalExitBorder();
            square.addImage(HORIZONTAL_EXIT);
        }

        // Get MapSquares adjacent vertically.
        MapSquare northSquare = grid.getSquareNorthOf(square.getPosition());
        MapSquare southSquare = grid.getSquareSouthOf(square.getPosition());
        // Check for rooms and null pointers.
        if (northSquare.isInBounds() && northSquare.isRoom()
                && southSquare.isInBounds() && southSquare.isRoom()) {
            square.setVerticalExitBorder();
            square.addImage(VERTICAL_EXIT);
        }

        // Get MapSquares adjacent diagonally southwest/northeast.
        MapSquare southwestSquare = grid.getSquareSouthwestOf(square.getPosition());
        MapSquare northeastSquare = grid.getSquareNortheastOf(square.getPosition());
        // Get MapSquares adjadcent diagonally northwest/southeast.
        MapSquare northwestSquare = grid.getSquareNorthwestOf(square.getPosition());
        MapSquare southeastSquare = grid.getSquareSoutheastOf(square.getPosition());

        // Check for rooms and null points on southwest/northeast and northwest/southeast axis.
        if (southwestSquare.isInBounds() && southwestSquare.isRoom()
                && northeastSquare.isInBounds() && northeastSquare.isRoom()
                && northwestSquare.isInBounds() && northwestSquare.isRoom()
                && southeastSquare.isInBounds() && southeastSquare.isRoom()) {
            square.addImage(X_EXIT);
        // Check for rooms and null pointers on the southwest/northeast axis.
        } else if (southwestSquare.isInBounds() && southwestSquare.isRoom()
                && northeastSquare.isInBounds() && northeastSquare.isRoom()) {
            square.addImage(FORWARD_DIAGONAL_EXIT);
        // Check for rooms and null pointers on the northwest/southeast axis.
        } else if (northwestSquare.isInBounds() && northwestSquare.isRoom()
                && southeastSquare.isInBounds() && southeastSquare.isRoom()) {
            square.addImage(BACK_DIAGONAL_EXIT);
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
            if(square.isExit()) {
                square.setExit(false);
            } else if(square.isRoom()) {
                square.setRoom(false);
            }
            square.restoreDefaultBorder();
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            square.setExit(true);
        }
    }
}
