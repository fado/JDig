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
     */
    public Map() {
        this.defaultCell = new Cell(new Point(-1, -1), this);
        this.allCells = new ArrayList<>();
    }
    
    /**
     * Adds a Cell to the Map.
     * 
     * @param cell - The Cell to be added.
     */
    public void addCell(Cell cell) {
        allCells.add(cell);
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
                return referenceCell;
            }
        }
        return defaultCell;
    }
    
    /**
     * Determines the potential Entity that could exist in a Cell, given its surroundings.
     * 
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