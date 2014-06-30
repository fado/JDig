package main;

import java.awt.Point;

public class Cell {
    
    public final int X;
    public final int Y;
    private Entity currentEntity;
    
    public Cell(Point point) {
        this.X = point.x;
        this.Y = point.y;
    }
    
    public void setEntity(Entity entity) {
        this.currentEntity = entity;
    }
    
    public boolean isRoom() {
        return currentEntity.equals(Entity.ROOM) && this.isInBounds();
    }
    
    private boolean isInBounds() {
        return this.X >= 0 && this.Y >= 0;
    }
}