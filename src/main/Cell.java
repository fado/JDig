package main;

import java.awt.Point;

public class Cell {

    public final int X;
    public final int Y;
    private final Map parentMap;
    private Entity currentEntity;

    public Cell(Point point, Map map) {
        this.X = point.x;
        this.Y = point.y;
        this.parentMap = map;
        this.currentEntity = Entity.NO_ENTITY;
    }

    public Map getParentMap() {
        return this.parentMap;
    }

    public void setEntity(Entity entity) {
        this.currentEntity = entity;
    }

    public Entity getEntity() {
        return this.currentEntity;
    }

    public boolean isFilled() {
        return !currentEntity.equals(Entity.NO_ENTITY);
    }

    public boolean isRoom() {
        return currentEntity.equals(Entity.ROOM) && this.isInBounds();
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
    public Entity getPotentialEntity() {
        // Check for rooms adjacent horizontally.
        Cell westCell = parentMap.getCellAdjacentTo(this, Direction.WEST);
        Cell eastCell = parentMap.getCellAdjacentTo(this, Direction.EAST);
        if (westCell.isRoom() && eastCell.isRoom()) {
            return Entity.HORIZONTAL_EXIT;
        }
        // Check for rooms adjacent vertically.
        Cell northCell = parentMap.getCellAdjacentTo(this, Direction.NORTH);
        Cell southCell = parentMap.getCellAdjacentTo(this, Direction.SOUTH);
        if (northCell.isRoom() && southCell.isRoom()) {
            return Entity.VERTICAL_EXIT;
        }
        // Check for rooms adjacent diagonally.
        Cell northwestCell = parentMap.getCellAdjacentTo(this, Direction.NORTHWEST);
        Cell northeastCell = parentMap.getCellAdjacentTo(this, Direction.NORTHEAST);
        Cell southwestCell = parentMap.getCellAdjacentTo(this, Direction.SOUTHWEST);
        Cell southeastCell = parentMap.getCellAdjacentTo(this, Direction.SOUTHEAST);
        // Check for rooms across both diagonal axis.
        if (northwestCell.isRoom() && northeastCell.isRoom()
                && southwestCell.isRoom() && southeastCell.isRoom()) {
            return Entity.X_EXIT;
        } // Check for rooms on the southwest/northeast axis.
        else if (southwestCell.isRoom() && northeastCell.isRoom()) {
            return Entity.FORWARD_DIAGONAL_EXIT;
        } // Check for rooms on the southeast/northwest axis.
        else if (southeastCell.isRoom() && northwestCell.isRoom()) {
            return Entity.BACKWARD_DIAGONAL_EXIT;
        }
        return Entity.NO_ENTITY;
    }
}
