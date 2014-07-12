package main;

import java.util.List;

public class Room {
    
    private String name;
    private List<ExitModel> exits;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
       return this.name;
    }
    
    public void addExit(ExitModel exit) {
        exits.add(exit);
    }
    
    public List<ExitModel> getExits() {
        return this.exits;
    }
    
}