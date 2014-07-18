package gui.info;

import data.Room;

public class SetLightCommand implements Command {

    @Override
    public void set(Room room, String text) {
        room.setLight(text);
    }
    
}