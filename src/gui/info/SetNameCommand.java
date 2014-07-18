package gui.info;

import data.Room;

public class SetNameCommand implements SetterCommand {

    @Override
    public void set(Room room, String text) {
        room.setName(text);
    }
    
}