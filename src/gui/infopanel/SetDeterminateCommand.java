package gui.infopanel;

import data.Room;

public class SetDeterminateCommand implements SetterCommand {

    @Override
    public void set(Room room, String text) {
        room.setDeterminate(text);
    }
    
}