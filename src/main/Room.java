package main;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Room {
   
    private final Point position;
    private final Map<String, Room> exits = new HashMap();
    
    public Room(Point point) {
        this.position = point;
    }
    
   public Point getPosition() {
       return this.position;
   }
   
   public void addExit(String name, Room destination) {
       exits.put(name, destination);
   }
   
    @Override
   public String toString() {
       return position.x +", "+position.y;
   }
}