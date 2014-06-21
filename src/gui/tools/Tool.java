package gui.tools;

import gui.Cell;

public interface Tool {
    public void mouseEntered(Cell cell);
    public void mouseExited(Cell cell);
    public void mouseClicked(Cell cell);
}