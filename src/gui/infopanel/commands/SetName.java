package gui.infopanel.commands;

import data.Room;

public class SetName implements SetterCommand {

    @Override
    public void set(Room room, String text) {
        room.setName(text);
    }
    
}