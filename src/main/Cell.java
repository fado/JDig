package main;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import main.entities.EntityType;
import main.entities.ExitType;
import main.entities.Room;

public class Cell {

    public final int X;
    public final int Y;
    private final Level parentMap;
    private EntityType currentEntity;
    private boolean filled;
    private Room room;

    public Cell(Point point, Level map) {
        this.X = point.x;
        this.Y = point.y;
        this.parentMap = map;
        this.currentEntity = EntityType.NO_ENTITY;
    }

    public Level getParentMap() {
        return this.parentMap;
    }

    public void setEntityType(EntityType entity) {
        if (entity == EntityType.NO_ENTITY) {
            this.setFilled(false);
        } else {
            if (entity == EntityType.ROOM) {
                room = new Room();
            }
            this.setFilled(true);
        }
        this.currentEntity = entity;
    }
    
    public Room getRoom() {
        return this.room;
    }
    
    public void setRoom() {
        this.room = new Room();
    }

    public void setFilled(boolean bool) {
        this.filled = bool;
    }
    
    public boolean isFilled() {
        return filled;
    }

    public boolean isRoom() {
        return currentEntity == EntityType.ROOM && this.isInBounds();
    }

    private boolean isInBounds() {
        return this.X >= 0 && this.Y >= 0;
    }

    /**
     * Determines the potential Entity that could exist in the Cell, given its
     * surroundings.
     *
     * @return - The potential Entity that could exist in the Cell.
     */
    public ExitType getPotentialExitType() {
        Map <String, Cell> cells = getAdjacentCells();
        // Check for rooms adjacent horizontally.
        if (cells.get("westCell").isRoom() && cells.get("eastCell").isRoom()) {
            return ExitType.HORIZONTAL_EXIT;
        }
        // Check for rooms adjacent vertically.
        if (cells.get("northCell").isRoom() && cells.get("southCell").isRoom()) {
            return ExitType.VERTICAL_EXIT;
        }
        // Check for rooms across both diagonal axis.
        if (cells.get("northwestCell").isRoom() && cells.get("northeastCell").isRoom()
                && cells.get("southwestCell").isRoom() && cells.get("southeastCell").isRoom()) {
            return ExitType.X_EXIT;
        } 
        // Check for rooms on the southwest/northeast axis.
        else if (cells.get("southwestCell").isRoom() && cells.get("northeastCell").isRoom()) {
            return ExitType.FORWARD_DIAGONAL_EXIT;
        } 
        // Check for rooms on the southeast/northwest axis.
        else if (cells.get("southeastCell").isRoom() && cells.get("northwestCell").isRoom()) {
            return ExitType.BACKWARD_DIAGONAL_EXIT;
        }
        return null;
    }
    
    public Map<String, Cell> getAdjacentCells() {
        Map <String, Cell> cells = new HashMap<>();
        cells.put("westCell", parentMap.getCellAdjacentTo(this, Direction.WEST));
        cells.put("eastCell", parentMap.getCellAdjacentTo(this, Direction.EAST));
        cells.put("northCell", parentMap.getCellAdjacentTo(this, Direction.NORTH));
        cells.put("southCell", parentMap.getCellAdjacentTo(this, Direction.SOUTH));
        cells.put("northwestCell", parentMap.getCellAdjacentTo(this, Direction.NORTHWEST));
        cells.put("northeastCell", parentMap.getCellAdjacentTo(this, Direction.NORTHEAST));
        cells.put("southwestCell", parentMap.getCellAdjacentTo(this, Direction.SOUTHWEST));
        cells.put("southeastCell", parentMap.getCellAdjacentTo(this, Direction.SOUTHEAST));
        return cells;
    }

}
