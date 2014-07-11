package main;

import java.awt.Point;
import main.entities.EntityType;
import main.entities.ExitType;
import main.entities.Room;

public class Cell {

    public final int X;
    public final int Y;
    private final Map parentMap;
    private EntityType currentEntity;
    private boolean filled;
    private Room room;

    public Cell(Point point, Map map) {
        this.X = point.x;
        this.Y = point.y;
        this.parentMap = map;
        this.currentEntity = EntityType.NO_ENTITY;
    }

    public Map getParentMap() {
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
        // Check for rooms adjacent horizontally.
        Cell westCell = parentMap.getCellAdjacentTo(this, Direction.WEST);
        Cell eastCell = parentMap.getCellAdjacentTo(this, Direction.EAST);
        if (westCell.isRoom() && eastCell.isRoom()) {
            return ExitType.HORIZONTAL_EXIT;
        }
        // Check for rooms adjacent vertically.
        Cell northCell = parentMap.getCellAdjacentTo(this, Direction.NORTH);
        Cell southCell = parentMap.getCellAdjacentTo(this, Direction.SOUTH);
        if (northCell.isRoom() && southCell.isRoom()) {
            return ExitType.VERTICAL_EXIT;
        }
        // Check for rooms adjacent diagonally.
        Cell northwestCell = parentMap.getCellAdjacentTo(this, Direction.NORTHWEST);
        Cell northeastCell = parentMap.getCellAdjacentTo(this, Direction.NORTHEAST);
        Cell southwestCell = parentMap.getCellAdjacentTo(this, Direction.SOUTHWEST);
        Cell southeastCell = parentMap.getCellAdjacentTo(this, Direction.SOUTHEAST);
        // Check for rooms across both diagonal axis.
        if (northwestCell.isRoom() && northeastCell.isRoom()
                && southwestCell.isRoom() && southeastCell.isRoom()) {
            return ExitType.X_EXIT;
        } // Check for rooms on the southwest/northeast axis.
        else if (southwestCell.isRoom() && northeastCell.isRoom()) {
            return ExitType.FORWARD_DIAGONAL_EXIT;
        } // Check for rooms on the southeast/northwest axis.
        else if (southeastCell.isRoom() && northwestCell.isRoom()) {
            return ExitType.BACKWARD_DIAGONAL_EXIT;
        }
        return null;
    }
}
