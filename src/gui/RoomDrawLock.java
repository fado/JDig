package gui;

import data.Entity;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class RoomDrawLock {

    private boolean xEven = false;
    private boolean yEven = false;
    private boolean isSet = false;

    public void setRoomDrawLock(Entity entity) {
        if (isEven(entity.X)) {
            xEven = true;
        }
        if (isEven(entity.Y)) {
            yEven = true;
        }
        this.isSet = true;
    }

    public boolean canDrawAt(Entity entity) {
        return !this.isSet || isEven(entity.X) == xEven && isEven(entity.Y) == yEven;
    }

    private boolean isEven(int coordinate) { return coordinate % 2 == 0; }

    public boolean isSet() { return isSet; }

    public void resetRoomDrawLock() {
        this.isSet = false;
        this.xEven = false;
        this.yEven = false;
    }

}