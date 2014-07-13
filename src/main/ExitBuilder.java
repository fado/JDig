package main;

import data.Cell;
import data.Level;

public class ExitBuilder {
    
    private ExitBuilder() {
        
    }
    
    public static void build(Cell cell) {
        ExitDirection exit = cell.getPotentialExitDirection();
        
        
        if (exit == ExitDirection.HORIZONTAL_EXIT) {
            
        }
    }
}