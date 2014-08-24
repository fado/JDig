package tools;

import data.Cell;

/**
 * Sets up a restriction as to where Rooms can be placed within a Level.
 */
public class PlacementRestriction {

    private boolean xEven = false;
    private boolean yEven = false;
    private boolean isSet = false;

    /**
     * Default constructor.
     */
    public PlacementRestriction() {

    }

    /**
     * Sets or overrides the placement restriction to key off the passed-in Cell.
     * @param cell The Cell containing the new Room off which the placement
     *             restriction should key.
     */
    public void setPlacementRestriction(Cell cell) {
        if (isEven(cell.X)) {
            xEven = true;
        }
        if (isEven(cell.Y)) {
            yEven = true;
        }
        this.isSet = true;
    }

    /**
     * Compares the parity of the coordinates of the Cell passed-in with
     * the parity of the coordinates of the Cell which contains the first
     * Room placed in the Level.
     * @param cell The Cell whose parity you want to test.
     * @return true if the parity matches, otherwise false.
     */
    public boolean positionIsValid(Cell cell) {
        return !this.isSet || isEven(cell.X) == xEven && isEven(cell.Y) == yEven;
    }

    /**
     * Determines the parity of the passed-in integer.
     * @param coordinate The coordinate under test.
     * @return True if parity is even, otherwise false.
     */
    private boolean isEven(int coordinate) {
        return coordinate % 2 == 0;
    }

    /**
     * Determines whether or not a placement restriction has been set.
     * @return true if a restriction is in place, otherwise false.
     */
    public boolean isSet() {
        return isSet;
    }

    /**
     * Resets the Cell containing the first room and the xEven and yEven booleans.
     */
    public void resetPlacementRestriction() {
        this.isSet = false;
        this.xEven = false;
        this.yEven = false;
    }

}
