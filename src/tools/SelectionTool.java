package tools;

import java.awt.event.MouseEvent;
import main.Cell;

public class SelectionTool implements Tool {

    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
        if(cell.isRoom()) {
            cell.getRoom();
        }
    }
    
}