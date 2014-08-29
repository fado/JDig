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

/**
 * A Street within a Level.  A named collection of Rooms.
 */
public class Street {

    private final String name;
    private final List<Room> rooms;

    /**
     * Default constructor.
     * @param name The name of the Street.
     */
    public Street(String name) {
        this.rooms = new ArrayList<>();
        this.name = name;
    }

    /**
     * Get the name of the Street.
     * @return the name of the Street.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Add a Room to the Street.
     * @param room The Room to be added.
     */
    public void addRoom(Room room) {
        rooms.add(room);
        room.setStreet(this.name);
    }

    /**
     * Returns a list of all Rooms in the Street.
     * @return a list of all Rooms in the Street.
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Returns the number of rooms in the Street.
     * @return the number of rooms in the Street.
     */
    public int size() {
        return rooms.size();
    }

}
