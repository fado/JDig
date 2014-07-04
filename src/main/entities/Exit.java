package main.entities;

public interface Exit extends Entity {
 
    public void setDestination(Room room);
    public Room getDestination();
    
}
