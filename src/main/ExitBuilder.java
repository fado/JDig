package main;

import data.Cell;
import data.Level;

public class ExitBuilder {
    
    private ExitBuilder() {
        
    }
    
    public static void build(Cell cell) {
        ExitImage exit = cell.getPotentialExitType();
        
        
        if (exit == ExitImage.HORIZONTAL_EXIT) {
            
        }
    }
}