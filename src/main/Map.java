package main;

import java.util.ArrayList;
import java.util.List;

public class Map {
    
    private final List<Cell> allCells;
    private final Cell defaultCell;
    
    public Map(int maxColumns, int maxRows) {
        this.defaultCell = new Cell(-1, -1);
        
        this.allCells = new ArrayList<>();
        for (int rows = 0; rows < maxRows; rows++) {
            for (int columns = 0; columns < maxColumns; columns++) {
                Cell cell = new Cell(columns, rows);
                allCells.add(cell);
            }
        }
    }
    
    /**
     * Gets the Cell adjacent to the passed-in Cell, in the passed-in Direction.
     * Each member of the Direction enum knows the offsets that should be applied
     * to the X and Y coordinates of the passed-in Cell to get the coordinates of the 
     * Cell that lies adjacent in that Direction.  This method returns that Cell
     * if it exists, otherwise it returns a default cell with the coordinates (-1, -1).
     * 
     * @param cell - The Cell that is the point of reference.
     * @param direction - Specifies in which direction relative to the passed-in
     * Cell that we want to look for an adjacent Cell.
     * @return - The adjacent cell.
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
     * This method determines the potential Entity that could exist in a Cell,
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