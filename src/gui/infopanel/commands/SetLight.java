package gui.infopanel.commands;

import data.Room;

public class SetLight implements SetterCommand {

    @Override
    public void set(Room room, String text) {
        room.setLight(text);
    }
    
}