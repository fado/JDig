package gui.info;

import data.Room;

public class SetLightCommand implements SetterCommand {

    @Override
    public void set(Room room, String text) {
        room.setLight(text);
    }
    
}