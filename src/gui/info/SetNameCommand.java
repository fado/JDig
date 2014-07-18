package gui.info;

import data.Room;

public class SetNameCommand implements Command {

    @Override
    public void set(Room room, String text) {
        room.setName(text);
    }
    
}