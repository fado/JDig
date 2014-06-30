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
}