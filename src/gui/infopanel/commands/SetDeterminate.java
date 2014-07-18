package gui.infopanel.commands;

import data.Room;

public class SetDeterminate implements SetterCommand {

    @Override
    public void set(Room room, String text) {
        room.setDeterminate(text);
    }
    
}