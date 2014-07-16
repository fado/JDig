package data;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph 
 * developers.
 * Copyright (C) 2014 Fado@Epitaph.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.List;

public class Street {
    
    private final String name;
    private final List<Room> rooms;
    
    public Street(String name) {
        this.rooms = new ArrayList<>();
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void addRoom(Room room) {
        rooms.add(room);
        room.setStreet(this.name);
    }
    
    public List<Room> getRooms() {
        return rooms;
    }
}