package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class LevelModel {
    
    private final List<CellModel> allCells;
    private final CellModel defaultCell;
    
    public LevelModel(int maxColumns, int maxRows) {
        this.defaultCell = new CellModel(new Point(-1, -1), this);
        this.allCells = new ArrayList<>();
        
        for (int rows = 0; rows < maxColumns; rows++) {
            for (int columns = 0; columns < maxRows; columns++) {
                CellModel cell = new CellModel(new Point(columns, rows), this);
                this.addCell(cell);
            }
        }
        
    }
    
    /**
     * Adds a Cell to the Map.
     * 
     * @param cell - The Cell to be added.
     */
    private void addCell(CellModel cell) {
        allCells.add(cell);
    }
    
    /**
     * Gets the Cell at the specified Point.
     * 
     * @param point - The Point at which the cell lies.
     * @return - The Cell at the specified point.
     */
    public CellModel getCellAt(Point point) {
        for (CellModel cell : allCells) {
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
    public List<CellModel> getAllCells() {
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
    public CellModel getCellAdjacentTo(CellModel referenceCell, Direction direction) {
        for (CellModel cell : allCells) {
            if(cell.X == direction.translateX(referenceCell) && 
                    cell.Y == direction.translateY(referenceCell)) {
                return cell;
            }
        }
        return defaultCell;
    }

}