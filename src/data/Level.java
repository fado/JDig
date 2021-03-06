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

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The Level class holds a collection of Cells that make up a single area.
 */
public class Level {

    private final List<Cell> allCells;
    private final Cell defaultCell;
    private final List<Room> rooms = new ArrayList<>();
    private final List<Street> streets = new ArrayList<>();

    /**
     * Constructor builds a Level comprised of a number of Cells, spanning
     * the number of rows and columns passed in.
     */
    public Level() {
        this.defaultCell = new Cell(new Point(-1, -1), this);
        this.allCells = new ArrayList<>();
    }

    /**
     * Add a new Cell to the Level.
     * @param cell The new Cell.
     */
    public void addCell(Cell cell) {
        allCells.add(cell);
    }

    /**
     * Add a Street to the Level.
     * @param street The new Street.
     */
    public void addStreet(Street street) {
        streets.add(street);
    }

    /**
     * Remove a Street from the Level.
     * @param street The Street to be removed.
     */
    public void removeStreet(Street street) {
        streets.remove(street);
    }

    /**
     * Return a List of Streets in the Level.
     * @return a List of Streets in the Level.
     */
    public List<Street> getStreets() {
        return this.streets;
    }

    /**
     * Returns a single Street from the List of Streets contained within the Level
     * that matches the String passed-in.
     * @param streetName The name of the Street to be retrieved.
     * @return the Street whose name matches the one supplied.
     */
    public Street getStreet(String streetName) {
        Street streetToBeReturned = null;
        for (Street street : streets) {
            if (street.getName().equalsIgnoreCase(streetName)) {
                streetToBeReturned = street;
            }
        }
        return streetToBeReturned;
    }

    /**
     * Returns the Cell at a specified Point within the Level.
     * @param point The point at which the Cell exists.
     * @return the Cell found, or if no Cell is found which matches the coordinates
     * contained within the Point, the default Cell is returned.
     */
    public Cell getCellAt(Point point) {
        for (Cell cell : allCells) {
            if (cell.X == point.x && cell.Y == point.y) {
                return cell;
            }
        }
        return defaultCell;
    }

    /**
     * Returns a list of all Cells within the Level.
     * @return all Cells within the Level.
     */
    public List<Cell> getAllCells() {
        return this.allCells;
    }

    /**
     * Registers a Room with the Level.
     * @param room The Room to be registered.
     */
    public void registerRoom(Room room) {
        rooms.add(room);
    }

    /**
     * Unregister a Room from the Level.
     * @param room The Room to be unregistered.
     */
    public void unregisterRoom(Room room) {
        rooms.remove(room);
    }

    /**
     * Returns a list of all Rooms registered to the Level.
     * @return a list of all Cells within the Level that contain Rooms.
     */
    public List<Room> getRooms() {
        return rooms;
    }

    /**
     * Finds the Cell that lies adjacent to the passin Cell, in the passed-in
     * Direction.  A method within the Direction enumerator is used to perform
     * the necessary coordinate translation for the given Direction.
     * @param referenceCell The Cell that is used as the reference point.
     * @param direction The direction in which you want to look for an adjacent Cell.
     * @return the Cell found.
     */
    public Cell getCellAdjacentTo(Cell referenceCell, Direction direction) {
        for (Cell cell : allCells) {
            if (cell.X == direction.translateX(referenceCell) &&
                    cell.Y == direction.translateY(referenceCell)) {
                return cell;
            }
        }
        return defaultCell;
    }

}
