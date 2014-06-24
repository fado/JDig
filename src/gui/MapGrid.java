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
    
    // The default dimensions of each cell, expressed in pixels. (Was 15)
    private final int SIZE = 200;
    private Tool selectedTool;
    private final List<MapSquare> squares;
    
    /**
     * Creates a Grid object with its fields set to the passed-in arguments.
     * @param maxRows - Size of the grid on the y axis.
     * @param maxColumns - Size of the grid on the x axis.
     */
    public MapGrid(int maxRows, int maxColumns) {
        squares = new ArrayList<>();
        
        // Set default tool selection.
        selectedTool = new DefaultPointer();
        
        // Setup grid.
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        for(int row = 0; row < maxRows; row++) {
            for(int column = 0; column < maxColumns; column++) {
                constraints.gridx = column;
                constraints.gridy = row;
                
                MapSquare square = new MapSquare(this, SIZE, row, column);
                squares.add(square);  // Gives us an easy way to access MapSquares.
                add(square, constraints);
            }
        }
    }
    
    /**
     * Searches through all MapSquares in the MapGrid and returns whatever square
     * matches the passed in Point.
     * @param point - The location of the MapSquare being searched for.
     * @return - Returns the MapSquare if found, otherwise null.
     */
    public MapSquare getCellAt(Point point) {
        for(MapSquare cell : squares) {
            if(cell.getPosition() == point) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Find and return the MapSquare to the west (x-1) of the specified Point.
     * @param point - The reference Point.
     * @return - The MapSquare to the west (x-1) of the specified Point.
     */
    public MapSquare getSquareWestOf(Point point) {
        for(MapSquare square : squares) {
           if(square.getPosition().x == point.x - 1 && square.getPosition().y == point.y) {
               return square;
           }
        }
        return null;
    }
    
    /**
     * Find and return the MapSquare to the east (x+1) of the specified Point.
     * @param point - The reference Point.
     * @return  - The MapSquare to the east (x+1) of the specified Point.
     */
    public MapSquare getSquareEastOf(Point point) {
        for(MapSquare square : squares) {
           if(square.getPosition().x == point.x + 1 && square.getPosition().y == point.y) {
               return square;
           }
        }
        return null;
    }
    
    /**
     * Find and return the MapSquare to the north (y-1) of the specified Point.
     * @param point - The reference Point.
     * @return - The MapSquare to the north (y-1) of the specified Point.
     */
    public MapSquare getSquareNorthOf(Point point) {
        for(MapSquare square : squares) {
           if(square.getPosition().x == point.x && square.getPosition().y == point.y - 1) {
               return square;
           }
        }
        return null;
    }
    
    /**
     * Find and return the MapSquare to the south (y+1) of the specified Point.
     * @param point - The reference Point.
     * @return - The MapSquare to the south (y+1) of the specified Point.
     */
    public MapSquare getSquareSouthOf(Point point) {
        for(MapSquare square : squares) {
           if(square.getPosition().x == point.x && square.getPosition().y == point.y + 1) {
               return square;
           }
        }
        return null;
    }
    
    /**
     * Find and return the MapSquare to the northwest (x-1, y-1) of the specified Point.
     * @param point - The reference Point.
     * @return - The MapSquare to the northwest (x-1, y-1) of the specified Point.
     */
    public MapSquare getSquareNorthwestOf(Point point) {
        for(MapSquare square : squares) {
           if(square.getPosition().x == point.x - 1 && square.getPosition().y == point.y - 1) {
               return square;
           }
        }
        return null;
    }
    
    /**
     * Find and return the MapSquare to the northeast (x+1, y-1) of the specified Point.
     * @param point - The reference Point.
     * @return - The MapSquare to the northeast (x+1, y-1) of the specified Point.
     */
    public MapSquare getSquareNortheastOf(Point point) {
        for(MapSquare square : squares) {
           if(square.getPosition().x == point.x + 1 && square.getPosition().y == point.y - 1) {
               return square;
           }
        }
        return null;
    }
    /**
     * Find and return the MapSquare to the southwest (x-1, y+1) of the specified Point.
     * @param point - The reference Point.
     * @return - The MapSquare to the southwest (x-1, y+1) of the specified Point.
     */
    public MapSquare getSquareSouthwestOf(Point point) {
        for(MapSquare square : squares) {
           if(square.getPosition().x == point.x - 1 && square.getPosition().y == point.y + 1) {
               return square;
           }
        }
        return null;
    }
    
    /**
     * Find and return the MapSquare to the southeast (x+1, y+1) of the specified Point.
     * @param point - The reference Point.
     * @return - The MapSquare to the southeast (x+1, y+1) of the specified Point.
     */
    public MapSquare getSquareSoutheastOf(Point point) {
        for(MapSquare square : squares) {
           if(square.getPosition().x == point.x + 1 && square.getPosition().y == point.y + 1) {
               return square;
           }
        }
        return null;
    }
    
    /**
     * Returns the currently selected tool.
     * @return - The currently selected tool.
     */
    public Tool getSelectedTool() {
        return this.selectedTool;
    }
    
    /**
     * Sets the currently selected tool.
     * @param tool - The currently selected tool.
     */
    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
    }
   
}