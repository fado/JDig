package gui.infopanel.commands;

import data.Room;

public class SetShort implements SetterCommand {
    
    @Override
    public void set(Room room, String text) {
        room.setShort(text);
    }
    
}