package main;

import gui.MainUI;
import java.io.IOException;
import java.util.Properties;

public class Jdig {
    
    private final int DEFAULT_ROWS = 40;
    private final int DEFAULT_COLUMNS = 40;
    private final Map currentMap;
    
    public static void main(String[] args) throws IOException {
        new Jdig();
    }
    
    private Jdig() {
        currentMap = new Map(DEFAULT_COLUMNS, DEFAULT_ROWS);
        MainUI gui = new MainUI(getCurrentMap());
        gui.run();
    }
    
    private Map getCurrentMap() {
        if(currentMap == null) {
            return new Map(DEFAULT_COLUMNS, DEFAULT_ROWS);
        }
        return currentMap;
    }
    
}