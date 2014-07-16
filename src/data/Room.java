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

public class Room {
    
    private String name = "";
    private String street;
    private final List<Exit> exits = new ArrayList<>();
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
       return this.name;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getStreet() {
        return this.street;
    }
    
    public void addExit(Exit exit) {
        exits.add(exit);
    }
    
    public List<Exit> getExits() {
        return this.exits;
    }
    
}