package main;

import java.util.ArrayList;
import java.util.List;

public class Map {
    
    private final List<Cell> cells;
    private final Cell defaultCell;
    
    public Map(int maxColumns, int maxRows) {
        this.defaultCell = new Cell(-1, -1);
        
        this.cells = new ArrayList<>();
        for (int rows = 0; rows < maxRows; rows++) {
            for (int columns = 0; columns < maxColumns; columns++) {
                Cell cell = new Cell(columns, rows);
                cells.add(cell);
            }
        }
    }
    
    public Cell getSquareAt(Direction direction) {
        for(Cell cell : cells) {
            if(cell.X == direction.getXOffset(cell) && cell.Y == direction.getYOffset(cell)) {
                return cell;
            }
        }
        return defaultCell;
    }
}