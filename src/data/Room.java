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
import properties.Localization;

public class Room {

    private String include = "";
    private String inherit = "";
    private String name = "";
    private String street = "";
    private String longDescription = "";
    private String shortDescription = "";
    private String determinate = "";
    private String light;
    private final List<Exit> exits = new ArrayList<>();
    private static final Localization localization = new Localization();
    
    public Room() {
        this.include = localization.get("DefaultInclude");
        this.inherit = localization.get("DefaultInherit");
        this.longDescription = localization.get("DefaultLongDescription");
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
       return this.name;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getInclude() {
        return this.include;
    }

    public void setInherit(String inherit) {
        this.inherit = inherit;
    }

    public String getInherit() {
        return this.inherit;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    public String getStreet() {
        return this.street;
    }
    
    public void setShort(String description) {
        this.shortDescription = description;
    }
    
    public String getShort() {
        return this.shortDescription;
    }
    
    public void setDeterminate(String determinate) {
        this.determinate = determinate;
    }
    
    public String getDeterminate() {
        return this.determinate;
    }
    
    public void setLight(String light) {
        this.light = light;
    }
    
    public String getLight() {
        return this.light;
    }
    
    public void setLong(String description) {
        this.longDescription = description;
    }
    
    public String getLong() {
        return this.longDescription;
    }
    
    public void addExit(Exit exit) {
        exits.add(exit);
    }

    public void removeExit(Exit exit) {
        exits.remove(exit);
    }
    
    public List<Exit> getExits() {
        return this.exits;
    }
    
}