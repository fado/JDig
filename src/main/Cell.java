package main;

public class Cell {
    
    public final int X;
    public final int Y;
    private boolean isRoom;
    private boolean isExit;
    
    public Cell(int column, int row) {
        this.X = column;
        this.Y = row;
    }
}