package tools;

import gui.CellPanel;
import java.awt.event.MouseEvent;

public interface Tool {
    public void mouseEntered(CellPanel square, MouseEvent event);
    public void mouseExited(CellPanel square, MouseEvent event);
    public void mouseClicked(CellPanel square, MouseEvent event);
}