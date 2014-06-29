package main;

/**
 * Possible compass directions within the MUD's exit structure.
 */
public enum Direction {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0),
    NORTHEAST(1, -1),
    NORTHWEST(-1, -1),
    SOUTHEAST(1, 1),
    SOUTHWEST(-1, 1);
    
    public final int xOffset;
    public final int yOffset;
    
    Direction (int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public int getX(Cell cell) {
        return cell.X + this.xOffset;
    }
    
    public int getY(Cell cell) {
        return cell.Y + this.yOffset;
    }
}