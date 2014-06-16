package main;

public class Room {
    private final int xCoord;
    private final int yCoord;
    
    public Room(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    
    @Override
    public String toString() {
        return String.valueOf(xCoord) +", "+ String.valueOf(yCoord);
    }
}