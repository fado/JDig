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

public class Level {
    
    private final List<Cell> allCells;
    private final Cell defaultCell;
    private final List<Street> streets = new ArrayList<>();
    
    public Level(int maxColumns, int maxRows) {
        this.defaultCell = new Cell(new Point(-1, -1), this);
        this.allCells = new ArrayList<>();
        
        for (int rows = 0; rows < maxColumns; rows++) {
            for (int columns = 0; columns < maxRows; columns++) {
                Cell cell = new Cell(new Point(columns, rows), this);
                this.addCell(cell);
            }
        }
        
    }
    
    public void addStreet(Street street) {
        streets.add(street);
    }
    
    public void removeStreet(Street street) {
        streets.remove(street);
    }
    
    public List<Street> getStreets() {
        return this.streets;
    }

    public Street getStreet(String streetName) {
        Street streetToBeReturned = null;
        for(Street street : streets) {
            if(street.getName().equalsIgnoreCase(streetName)) {
                streetToBeReturned = street;
            }
        }
        return streetToBeReturned;
    }
    
    private void addCell(Cell cell) {
        allCells.add(cell);
    }

    public Cell getCellAt(Point point) {
        for (Cell cell : allCells) {
            if (cell.X == point.x && cell.Y == point.y) {
                return cell;
            }
        }
        return defaultCell;
    }
    
    public List<Cell> getAllCells() {
        return this.allCells;
    }

    public List<Room> getRooms() {
        List<Room> rooms = new ArrayList<>();
        for(Cell cell : allCells) {
            if(cell.isRoom()) {
                rooms.add(cell.getRoom());
            }
        }
        return rooms;
    }
    
    public Cell getCellAdjacentTo(Cell referenceCell, Direction direction) {
        for (Cell cell : allCells) {
            if(cell.X == direction.translateX(referenceCell) && 
                    cell.Y == direction.translateY(referenceCell)) {
                return cell;
            }
        }
        return defaultCell;
    }

}
