package gui.info;

import data.Room;

public class SetShortCommand implements SetterCommand {
    
    @Override
    public void set(Room room, String text) {
        room.setShort(text);
    }
    
}