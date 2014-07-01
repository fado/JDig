package main;

import gui.MainUI;
import java.awt.Point;

public class MapLoader {
    
    private final int DEFAULT_ROWS = 40;
    private final int DEFAULT_COLUMNS = 40;
    
    public void newMap() {
        load(new Map());
    }

    public void load(Map map) {
        if (map.getAllCells().isEmpty()) {
            for (int rows = 0; rows < DEFAULT_ROWS; rows++) {
                for (int columns = 0; columns < DEFAULT_COLUMNS; columns++) {
                    Cell cell = new Cell(new Point(columns, rows), map);
                    map.addCell(cell);
                }
            }
        }
        MainUI gui = new MainUI(map);
        gui.run();
    }
}
