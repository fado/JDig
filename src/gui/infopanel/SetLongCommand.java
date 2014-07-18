package gui.infopanel;

import data.Room;

public class SetLongCommand implements SetterCommand {

    @Override
    public void set(Room room, String text) {
        room.setLong(text);
    }
    
}