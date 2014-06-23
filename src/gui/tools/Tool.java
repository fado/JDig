package gui.tools;

import gui.MapSquare;

public interface Tool {
    public void mouseEntered(MapSquare square);
    public void mouseExited(MapSquare square);
    public void mouseClicked(MapSquare square);
}