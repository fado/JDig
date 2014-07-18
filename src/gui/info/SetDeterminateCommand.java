package gui.info;

import data.Room;

public class SetDeterminateCommand implements Command {

    @Override
    public void set(Room room, String text) {
        room.setDeterminate(text);
    }
    
}