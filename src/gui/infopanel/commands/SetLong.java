package gui.infopanel.commands;

import data.Room;

public class SetLong implements SetterCommand {

    @Override
    public void set(Room room, String text) {
        room.setLong(text);
    }
    
}