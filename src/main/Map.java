package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map {
    
    private final List<Cell> allCells;
    private final Cell defaultCell;
    
    /**
     * Creates a new Map object with the passed-in parameters.
     * 
     * @param maxColumns - Maximum number of Cells on the X axis.
     * @param maxRows  - Maximum number of Cells on the Y axis.
     */
    public Map(int maxColumns, int maxRows) {
        this.defaultCell = new Cell(new Point(-1, -1));
        
        this.allCells = new ArrayList<>();
        for (int rows = 0; rows < maxRows; rows++) {
            for (int columns = 0; columns < maxColumns; columns++) {
                Cell cell = new Cell(new Point(columns, rows));
                allCells.add(cell);
            }
        }
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
     * @param cell - The point of reference.
     * @param direction - The direction in which we are looking.
     * @return - The Cell found.
     */
    public Cell getCellAdjacentTo(Cell cell, Direction direction) {
        for (Cell aCell : allCells) {
            if(aCell.X == direction.getX(cell) && aCell.Y == direction.getY(cell)) {
                return cell;
            }
        }
        return defaultCell;
    }
    
    /**
     * Determines the potential Entity that could exist in a Cell,
     * given its surroundings.
     * @param cell - The Cell we are querying.
     * @return - The potential Entity that could exist in the Cell.
     */
    public Entity getPotentialEntity(Cell cell) {
        // Check for rooms adjacent horizontally.
        Cell westCell = getCellAdjacentTo(cell, Direction.WEST);
        Cell eastCell = getCellAdjacentTo(cell, Direction.EAST);
        if(westCell.isRoom() && eastCell.isRoom()) {
            return Entity.HORIZONTAL_EXIT;
        }
        // Check for rooms adjacent vertically.
        Cell northCell = getCellAdjacentTo(cell, Direction.NORTH);
        Cell southCell = getCellAdjacentTo(cell, Direction.SOUTH);
        if(northCell.isRoom() && southCell.isRoom()) {
            return Entity.VERTICAL_EXIT;
        }
        // Check for rooms adjacent diagonally.
        Cell northwestCell = getCellAdjacentTo(cell, Direction.NORTHWEST);
        Cell northeastCell = getCellAdjacentTo(cell, Direction.NORTHEAST);
        Cell southwestCell = getCellAdjacentTo(cell, Direction.SOUTHWEST);
        Cell southeastCell = getCellAdjacentTo(cell, Direction.SOUTHEAST);
        // Check for rooms across both diagonal axis.
        if(northwestCell.isRoom() && northeastCell.isRoom() && 
                southwestCell.isRoom() && southeastCell.isRoom()) {
            return Entity.X_EXIT;
        } 
        // Check for rooms on the southwest/northeast axis.
        else if (southwestCell.isRoom() && northeastCell.isRoom()) {
            return Entity.FORWARD_DIAGONAL_EXIT;
        }
        // Check for rooms on the southeast/northwest axis.
        else if (southeastCell.isRoom() && northwestCell.isRoom()) {
            return Entity.BACKWARD_DIAGONAL_EXIT;
        }
    return Entity.NO_ENTITY;
    }

}