package gui.tools;

import gui.MapSquare;
import java.awt.event.MouseEvent;

public interface Tool {
    public void mouseEntered(MapSquare square, MouseEvent event);
    public void mouseExited(MapSquare square, MouseEvent event);
    public void mouseClicked(MapSquare square, MouseEvent event);
}