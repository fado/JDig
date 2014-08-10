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
 * The Level class represents a collection of Cells that make up a single area.
 * @author Padraig Donnelly
 */
public class Level {

    private final List<Cell> allCells;
    private final Cell defaultCell;
    private final List<Street> streets = new ArrayList<>();

    public Level(int maxColumns, int maxRows) {
        this.defaultCell = new Cell(new Point(-1, -1), this);
        this.allCells = new ArrayList<>();
        populateLevel(maxColumns, maxRows);
    }

    /**
     * Populates the Level with Cells.
     * @param maxColumns Maximum number of columns within the Level.
     * @param maxRows Maximum number of Rows within the Level.
     */
    private void populateLevel(int maxColumns, int maxRows) {
        for (int rows = 0; rows < maxRows; rows++) {
            for (int columns = 0; columns < maxColumns; columns++) {
                Cell cell = new Cell(new Point(columns, rows), this);
                this.addCell(cell);
            }
        }
    }

    /**
     * Add a new Cell to the Level.
     * @param cell The Cell to be added.
     */
    private void addCell(Cell cell) {
        allCells.add(cell);
    }

    /**
     * Add a Street to the Level.
     * @param street The Street to be added.
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
     * Get a List of Streets in the Level.
     * @return a List of Streets in the Level.
     */
    public List<Street> getStreets() {
        return this.streets;
    }

    /**
     * Get a single Street from the List of Streets contained within the Level.
     * Matches the Street by its name.
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
     * Get a Cell at a specified point within the Level.
     * @param point The point at which the Cell exists.
     * @return the Cell that exits at the point supplied.
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
     * Get a list of all Cells within the Level.
     * @return a list of all Cells within the Level.
     */
    public List<Cell> getAllCells() {
        return this.allCells;
    }

    /**
     * Iterates through all Cells in the Level and returns those that contain
     * Rooms.
     * @return a list of all Cells within the Level that contain Rooms.
     */
    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        for (Cell cell : allCells) {
            if (cell.isRoom()) {
                rooms.add(cell.getRoom());
            }
        }
        return rooms;
    }

    /**
     * Finds the Cell that lies adjacent to the pass-in Cell, in the passed-in
     * Direction.
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
