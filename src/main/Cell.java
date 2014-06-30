package main;

public class Cell {
    
    final int X;
    final int Y;
    private Entity currentEntity;
    
    public Cell(int column, int row) {
        this.X = column;
        this.Y = row;
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