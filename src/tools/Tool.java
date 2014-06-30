package tools;

import gui.SquareUI;
import java.awt.event.MouseEvent;

public interface Tool {
    public void mouseEntered(SquareUI square, MouseEvent event);
    public void mouseExited(SquareUI square, MouseEvent event);
    public void mousePressed(SquareUI square, MouseEvent event);
}