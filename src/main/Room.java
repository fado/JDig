package main;

import globals.Direction;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * This class specifies a room object within the Jdig application.
 */
public class Room {
   
    private final Point position;
    private Map<String, Room> exits;
    
    /**
     * Create a new Room object with the passed-in parameter.
     * @param point 
     */
    public Room(Point point) {
        this.position = point;
        
        if (exits == null) {
            exits = new HashMap();
        }
    }
    
    /**
     * Returns the position of the room in the map.
     * @return - A Point representing the position of the room in the map.
     */
    public Point getPosition() {
        return this.position;
    }
   
    /**
     * Adds an exit to this room.
     * @param direction - The compass direction in which the exit leads.
     * @param destination - The Room to which the exit leads.
     */
    public void addExit(Direction direction, Room destination) {
        exits.put(direction.toString(), destination);
    }
   
    /**
     * Returns a list of exits leading out of the room.
     * @return - A List of exits leading out of the room.
     */
    public Map<String, Room> getExits() {
        return this.exits;
    }
   
    /**
     * Overridden toString() method.
     * @return - A String containing the x and y map coordinates of the room.
     */
    @Override
    public String toString() {
        return position.x +", "+position.y;
    }
}