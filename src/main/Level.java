package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Level {
    
    private final List<Cell> allCells;
    private final Cell defaultCell;
    
    public Level(int maxColumns, int maxRows) {
        this.defaultCell = new Cell(new Point(-1, -1), this);
        this.allCells = new ArrayList<>();
        
        for (int rows = 0; rows < maxColumns; rows++) {
            for (int columns = 0; columns < maxRows; columns++) {
                Cell cell = new Cell(new Point(columns, rows), this);
                this.addCell(cell);
            }
        }
        
    }
    
    /**
     * Adds a Cell to the Map.
     * 
     * @param cell - The Cell to be added.
     */
    private void addCell(Cell cell) {
        allCells.add(cell);
    }
    
    /**
     * Gets the Cell at the specified Point.
     * 
     * @param point - The Point at which the cell lies.
     * @return - The Cell at the specified point.
     */
    public Cell getCellAt(Point point) {
        for (Cell cell : allCells) {
            if (cell.X == point.x && cell.Y == point.y) {
                return cell;
            }
        }
        return defaultCell;
    }
    
    /**
     * Returns a List of all Cells in the Map.
     * 
     * @return - A List containing all Cells in the Map.
     */
    public List<Cell> getAllCells() {
        return this.allCells;
    }
    
    /**
     * Returns the Cell adjacent to the passed-in Cell that lies
     * in the passed-in Direction.
     * 
     * @param referenceCell - The point of reference.
     * @param direction - The direction in which we are looking.
     * @return - The Cell found.
     */
    public Cell getCellAdjacentTo(Cell referenceCell, Direction direction) {
        for (Cell cell : allCells) {
            if(cell.X == direction.translateX(referenceCell) && 
                    cell.Y == direction.translateY(referenceCell)) {
                return cell;
            }
        }
        return defaultCell;
    }

}