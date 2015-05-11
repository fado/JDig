package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class Level {

    private final List<Cell> allCells;
    private final Cell defaultCell;
    Logger logger = LoggerFactory.getLogger(Level.class);

    public Level() {
        this.defaultCell = new Cell(new Point(-1, -1));
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

    public Cell getCellAt(Point point) {
        for (Cell cell : allCells) {
            if (cell.X == point.x && cell.Y == point.y) {
                return cell;
            }
        }
        return defaultCell;
    }

    public ConnectionType getPotentialConnectionTypeAt(Point point) {
        Map<String, Cell> cells = getAllCellsAdjacentTo(point);

        if (cells.get("westCell").isConnectible()
                && cells.get("eastCell").isConnectible()) {
            return ConnectionType.HORIZONTAL;
        }
        if (cells.get("northCell").isConnectible()
                && cells.get("southCell").isConnectible()) {
            return ConnectionType.VERTICAL;
        }
        if (cells.get("northwestCell").isConnectible()
                && cells.get("northeastCell").isConnectible()
                && cells.get("southwestCell").isConnectible()
                && cells.get("southeastCell").isConnectible()) {
            return ConnectionType.X;
        } else if (cells.get("southwestCell").isConnectible()
                && cells.get("northeastCell").isConnectible()) {
            return ConnectionType.FORWARD_DIAGONAL;
        } else if (cells.get("southeastCell").isConnectible()
                && cells.get("northwestCell").isConnectible()) {
            return ConnectionType.BACKWARD_DIAGONAL;
        }
        return ConnectionType.NONE;
    }

    public Map<String, Cell> getAllCellsAdjacentTo(Point point) {
        Map<String, Cell> cells = new HashMap<>();
        Direction[] directions = Direction.values();
        String[] cellNames = {
                "northCell",
                "southCell",
                "eastCell",
                "westCell",
                "northeastCell",
                "northwestCell",
                "southeastCell",
                "southwestCell"
        };

        for (int counter = 0; counter < cellNames.length; counter++) {
            cells.put(cellNames[counter], getCellAdjacentTo(point, directions[counter]));
        }
        return cells;
    }

    public Cell getCellAdjacentTo(Point referencePoint, Direction direction) {
        for (Cell cell : allCells) {
            if (cell.X == direction.translateX(referencePoint.x) &&
                    cell.Y == direction.translateY(referencePoint.y)) {
                return cell;
            }
        }
        return defaultCell;
    }

}
