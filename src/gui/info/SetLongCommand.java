package gui.info;

import data.Room;

public class SetLongCommand implements Command {

    @Override
    public void set(Room room, String text) {
        room.setLong(text);
    }
    
}