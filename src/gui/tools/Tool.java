package gui.tools;

import gui.MapSquare;

public interface Tool {
    public void mouseEntered(MapSquare cell);
    public void mouseExited(MapSquare cell);
    public void mouseClicked(MapSquare cell);
}