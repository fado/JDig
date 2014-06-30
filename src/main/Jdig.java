package main;

import gui.MainUI;
import java.io.IOException;

public class Jdig {
    
    private final int DEFAULT_ROWS = 40;
    private final int DEFAULT_COLUMNS = 40;
    private final Map currentMap;
    
    public static void main(String[] args) throws IOException {
        new Jdig();
    }
    
    private Jdig() {
        currentMap = getCurrentMap();
        MainUI gui = new MainUI(currentMap);
        gui.run();
    }
    
    private Map getCurrentMap() {
        if(currentMap == null) {
            return new Map(DEFAULT_COLUMNS, DEFAULT_ROWS);
        }
        return currentMap;
    }
    
}