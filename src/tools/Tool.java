package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;
import main.Cell;

public interface Tool {
    public void mouseEntered(CellPanel cellPanel, Cell cell, MouseEvent event);
    public void mouseExited(CellPanel cellPanel, Cell cell, MouseEvent event);
    public void mouseClicked(CellPanel cellPanel, Cell cell, MouseEvent event);
}