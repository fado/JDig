package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class Level {

    private final List<Cell> allCells;
    private final Cell defaultCell;
    private final List<Room> rooms = new ArrayList<>();
    private final List<Street> streets = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(Level.class);

    public Level() {
        this.defaultCell = new Cell(new Point(-1, -1), this);
        this.allCells = new ArrayList<>();
    }

    public void addCell(Cell cell) {
        allCells.add(cell);
        logger.debug("Adding cell at {},{}", cell.X, cell.Y);
        logger.debug("Cells in level: {}", allCells.size());
    }

    public void removeCellAt(Point point) {
        allCells.remove(getCellAt(point));
        logger.debug("Removing cell from {},{}", point.x, point.y);
        logger.debug("Cells in level: {}", allCells.size());
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
        for (Street street : streets) {
            if (street.getName().equalsIgnoreCase(streetName)) {
                streetToBeReturned = street;
            }
        }
        return streetToBeReturned;
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

    public void registerRoom(Room room) {
        rooms.add(room);
    }

    public void unregisterRoom(Room room) {
        rooms.remove(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

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
