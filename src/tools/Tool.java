package tools;

import java.awt.event.MouseEvent;
import main.Cell;

public interface Tool {
    public void mouseEntered(Cell cell, MouseEvent event);
    public void mouseClicked(Cell cell, MouseEvent event);
}