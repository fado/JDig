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

import gui.levelpanel.CellPanel;
import properties.Images;
import properties.Localization;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.List;

/**
 * A single Room within the Level.
 */
public class Room implements Entity, Connectible {

    private String include = "";
    private String inherit = "";
    private String name = "";
    private String streetName;
    private String longDescription = "";
    private String shortDescription = "";
    private String determinate = "";
    private String light;
    private final List<Exit> exits = new ArrayList<>();
    private static final Localization localization = new Localization();
    private final Cell cell;

    /**
     * Constructor.
     */
    public Room(Cell cell) {
        this.cell = cell;
        this.include = localization.get("DefaultInclude");
        this.inherit = localization.get("DefaultInherit");
        this.longDescription = localization.get("DefaultLongDescription");
    }

    public Cell getCell() {
        return this.cell;
    }

    /**
     * Sets the name of this Room.
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this Room.
     * @return the name of the Room.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the #include file for this Room.
     * @param include The name of the #include file for the Room.
     */
    public void setInclude(String include) {
        this.include = include;
    }

    /**
     * Returns the name of the #include file for this Room.
     * @return the name of the #include file for this Room.
     */
    public String getInclude() {
        return this.include;
    }

    /**
     * Sets the name of the inheritable to be used by this Room.
     * @param inherit The name of the inheritable to be used by this Room.
     */
    public void setInherit(String inherit) {
        this.inherit = inherit;
    }

    /**
     * Returns the name of the inheritable to be used by this Room.
     * @return The name of the inheritable to be used by this Room.
     */
    public String getInherit() {
        return this.inherit;
    }

    /**
     * Sets the name of the Street to which this Room belongs.
     * @param streetName The name of the Street to which this Room belongs.
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Returns the name of the Street to which this Room belongs.
     * @return the name of the Street to which this Room belongs.
     */
    public String getStreetName() {
        return this.streetName;
    }

    /**
     * Sets the short description of this Room.
     * @param description The short description to be set.
     */
    public void setShort(String description) {
        this.shortDescription = description;
    }

    /**
     * Returns the short description of this Room.
     * @return the short description of this Room.
     */
    public String getShort() {
        return this.shortDescription;
    }

    /**
     * Sets the determinate for this Room.  This is the word that goes before
     * the short description when the Room is viewed in the MUD, e.g. 'the',
     * 'an' or 'a'.
     * @param determinate The determinate to be set.
     */
    public void setDeterminate(String determinate) {
        this.determinate = determinate;
    }

    /**
     * Returns the determinate for this Room.
     * @return the determinate for this Room.
     */
    public String getDeterminate() {
        return this.determinate;
    }

    /**
     * Sets the light level for this room.  Passed as a String within this
     * application as our ultimate goal is to generate a file, not interact with
     * the game engine.  Parsing as an int would be superfluous.
     * @param light The light level to be set.
     */
    public void setLight(String light) {
        this.light = light;
    }

    /**
     * Returns the light level for this Room.
     * @return the light level for this Room.
     */
    public String getLight() {
        return this.light;
    }

    /**
     * Sets the long description for this room.
     * @param description The long description to be set.
     */
    public void setLong(String description) {
        this.longDescription = description;
    }

    /**
     * Returns the long description for this Room.
     * @return the long description for this Room.
     */
    public String getLong() {
        return this.longDescription;
    }

    /**
     * Adds an Exit object to the Room.
     * @param exit The Exit object to be added.
     */
    public void addExit(Exit exit) {
        exits.add(exit);
    }

    /**
     * Removes an Exit object from the Room.
     * @param exit The Exit object to be removed.
     */
    public void removeExit(Exit exit) {
        exits.remove(exit);
    }

    /**
     * Returns a list of all Exit objects contained by the Room.
     * @return a list of all Exit objects contained by the Room.
     */
    public List<Exit> getExits() {
        return this.exits;
    }

    /**
     * Returns a specific Exit contained by the room, specified by Direction.
     * @param direction Direction of the Exit you wish to get.
     * @return the Exit contained by the Room that matches the passed-in Direction,
     * otherwise null.
     */
    public Exit getExit(Direction direction) {
        Exit exitToBeReturned = null;
        for (Exit exit : exits) {
            if (exit.getDirection() == direction) {
                exitToBeReturned = exit;
            }
        }
        return exitToBeReturned;
    }

    /**
     * Returns the normal image for the Entity.
     * @return the normal image for the Entity.
     */
    @Override
    public String getNormalImage() {
        Images images = new Images();
        return images.getImagePath("Room");
    }

    /**
     * Returns the selected image for the Entity.
     * @return the selected image for the Entity.
     */
    @Override
    public String getSelectedImage() {
        Images images = new Images();
        return images.getImagePath("SelectedRoom");
    }

    /**
     * Returns the Border for the Entity.
     * @return the Border for the Entity.
     */
    @Override
    public Border getBorder() {
        return null;
    }

}
