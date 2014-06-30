package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;

public interface Tool {
    public void mouseEntered(CellPanel cell, MouseEvent event);
    public void mouseExited(CellPanel cell, MouseEvent event);
    public void mouseClicked(CellPanel cell, MouseEvent event);
}