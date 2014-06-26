package gui;

import gui.tools.DefaultPointer;
import gui.tools.Tool;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * The MapGrid class specifies a collection of MapSquare that comprise the user
 * interface for drawing maps within JDig.
 */
public class MapGrid extends JPanel {

    private final int maxRows;
    private final int maxColumns;
    private Tool selectedTool;
    private List<MapSquare> squares;
    private final MapSquare defaultMapSquare;
    private final String ILLEGAL_POINT_MSG = "Point coordinates must be positive integers "+
            "and within the bounds of the MapGrid (i.e. within maxRows, maxColumns).";

    /**
     * Creates a Grid object with its fields set to the passed-in arguments.  Creates
     * a GridBagLayout maxRows by maxColumns in size and adds a new MapSquare to each
     * cell.  A List of MapSquares is also maintained so they can be more easily
     * accessed by other methods.
     *
     * @param maxRows - Size of the grid on the y axis.
     * @param maxColumns - Size of the grid on the x axis.
     */
    public MapGrid(int maxRows, int maxColumns) {
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
        // Create a default MapSquare at position -1x, -1y.
        defaultMapSquare = new MapSquare(this, -1, -1);
        selectedTool = new DefaultPointer();
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        if (squares == null) {
            squares = new ArrayList<>();
        }

        for (int row = 0; row < maxRows; row++) {
            for (int column = 0; column < maxColumns; column++) {
                constraints.gridx = column;
                constraints.gridy = row;

                MapSquare square = new MapSquare(this, row, column);
                squares.add(square);
                add(square, constraints);
            }
        }
    }

    /**
     * Searches through all MapSquares in the MapGrid and returns whatever
     * square matches the passed in Point.
     *
     * @param point - The location of the MapSquare being searched for.
     * @return - Returns the MapSquare if found, otherwise null.
     */
    public MapSquare getCellAt(Point point) {
        if (point.x < 0 || point.y < 0 || point.x > maxColumns || point.y > maxRows) {
            throw new IllegalArgumentException(ILLEGAL_POINT_MSG);
        }
        for (MapSquare cell : squares) {
            if (cell.getPosition() == point) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Find and return the MapSquare to the west (x-1) of the specified Point.
     *
     * @param point - The reference Point.
     * @return - The MapSquare to the west (x-1) of the specified Point.
     */
    public MapSquare getSquareWestOf(Point point) {
        for (MapSquare square : squares) {
            if (square.getPosition().x == point.x - 1 && square.getPosition().y == point.y) {
                return square;
            }
        }
        return defaultMapSquare;
    }

    /**
     * Find and return the MapSquare to the east (x+1) of the specified Point.
     *
     * @param point - The reference Point.
     * @return - The MapSquare to the east (x+1) of the specified Point.
     */
    public MapSquare getSquareEastOf(Point point) {
        for (MapSquare square : squares) {
            if (square.getPosition().x == point.x + 1 && square.getPosition().y == point.y) {
                return square;
            }
        }
        return defaultMapSquare;
    }

    /**
     * Find and return the MapSquare to the north (y-1) of the specified Point.
     *
     * @param point - The reference Point.
     * @return - The MapSquare to the north (y-1) of the specified Point.
     */
    public MapSquare getSquareNorthOf(Point point) {
        for (MapSquare square : squares) {
            if (square.getPosition().x == point.x && square.getPosition().y == point.y - 1) {
                return square;
            }
        }
        return defaultMapSquare;
    }

    /**
     * Find and return the MapSquare to the south (y+1) of the specified Point.
     *
     * @param point - The reference Point.
     * @return - The MapSquare to the south (y+1) of the specified Point.
     */
    public MapSquare getSquareSouthOf(Point point) {
        for (MapSquare square : squares) {
            if (square.getPosition().x == point.x && square.getPosition().y == point.y + 1) {
                return square;
            }
        }
        return defaultMapSquare;
    }

    /**
     * Find and return the MapSquare to the northwest (x-1, y-1) of the
     * specified Point.
     *
     * @param point - The reference Point.
     * @return - The MapSquare to the northwest (x-1, y-1) of the specified
     * Point.
     */
    public MapSquare getSquareNorthwestOf(Point point) {
        for (MapSquare square : squares) {
            if (square.getPosition().x == point.x - 1 && square.getPosition().y == point.y - 1) {
                return square;
            }
        }
        return defaultMapSquare;
    }

    /**
     * Find and return the MapSquare to the northeast (x+1, y-1) of the
     * specified Point.
     *
     * @param point - The reference Point.
     * @return - The MapSquare to the northeast (x+1, y-1) of the specified
     * Point.
     */
    public MapSquare getSquareNortheastOf(Point point) {
        for (MapSquare square : squares) {
            if (square.getPosition().x == point.x + 1 && square.getPosition().y == point.y - 1) {
                return square;
            }
        }
        return defaultMapSquare;
    }

    /**
     * Find and return the MapSquare to the southwest (x-1, y+1) of the
     * specified Point.
     *
     * @param point - The reference Point.
     * @return - The MapSquare to the southwest (x-1, y+1) of the specified
     * Point.
     */
    public MapSquare getSquareSouthwestOf(Point point) {
        for (MapSquare square : squares) {
            if (square.getPosition().x == point.x - 1 && square.getPosition().y == point.y + 1) {
                return square;
            }
        }
        return defaultMapSquare;
    }

    /**
     * Find and return the MapSquare to the southeast (x+1, y+1) of the
     * specified Point.
     *
     * @param point - The reference Point.
     * @return - The MapSquare to the southeast (x+1, y+1) of the specified
     * Point.
     */
    public MapSquare getSquareSoutheastOf(Point point) {
        for (MapSquare square : squares) {
            if (square.getPosition().x == point.x + 1 && square.getPosition().y == point.y + 1) {
                return square;
            }
        }
        return defaultMapSquare;
    }

    /**
     * Returns the currently selected tool.
     *
     * @return - The currently selected tool.
     */
    public Tool getSelectedTool() {
        return this.selectedTool;
    }

    /**
     * Sets the currently selected tool.
     *
     * @param tool - The currently selected tool.
     */
    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
    }

}
