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
    
    public Cell getCellRelativeTo(Cell cell, Direction direction) {
        for (Cell aCell : allCells) {
            if(aCell.X == direction.getX(cell) && aCell.Y == direction.getY(cell)) {
                return cell;
            }
        }
        return defaultCell;
    }
}