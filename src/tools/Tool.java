package tools;

import java.awt.event.MouseEvent;
import main.CellModel;

public interface Tool {
    public void mouseEntered(CellModel cell, MouseEvent event);
    public void mouseExited(CellModel cell, MouseEvent event);
    public void mouseClicked(CellModel cell, MouseEvent event);
}