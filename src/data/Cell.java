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

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * This is one Cell within the Level.
 */
public class Cell {

    public final int X;
    public final int Y;
    private final Level level;
    private Entity entity;
    private Color color;

    /**
     * Constructor.
     * @param point The Point at which the Cell lies.
     * @param level The Level in which the Cell is contained.
     */
    public Cell(Point point, Level level) {
        this.X = point.x;
        this.Y = point.y;
        this.level = level;
    }

    /**
     * Sets the current Entity contained by the Cell.
     * @param entity The new Entity.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Returns the Entity contained by the Cell.
     * @return the Entity contained by the Cell, or null if no Entity has been set.
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Returns the Level in which this Cell exists.
     * @return the parent Level of the Cell.
     */
    public Level getLevel() {
        return this.level;
    }

    // TO-DO: Move this.
    public void setColor(Color color) {
        this.color = color;
    }

    // TO-DO: Move this.
    public Color getColor() {
        return this.color;
    }

    /**
     * Tests for the presence of the marker interface Connectible on the Entity
     * contained by the Cell.
     * @return true if the marker interface is present, otherwise false.
     */
    public boolean isConnectible() {
        return entity != null && entity instanceof Connectible;
    }

    // TO-DO: Remove this.
    public boolean isExit() {
        return entity != null && !isConnectible();
    }

    /**
     * Tests whether or not this Cell is within valid bounds.
     * @return true if in bounds, false otherwise.
     */
    public boolean isInBounds() {
        return this.X >= 0 && this.Y >= 0;
    }

    /**
     * Returns the potential connection type for the Cell based on the Entities
     * in the surrounding Cells.
     * @return the potential connection type.
     */
    public ConnectionType getPotentialConnectionType() {
        Map<String, Cell> cells = getAdjacentCells();
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

    /**
     * Returns a Map of all Cells adjacent to this Cell.
     * @return a Map of all Cells adjacent to this Cell.
     */
    public Map<String, Cell> getAdjacentCells() {
        Map<String, Cell> cells = new HashMap<>();
        Direction.values();
        Direction[] directions = Direction.values();
        String[] cellNames = {"northCell", "southCell", "eastCell", "westCell",
                "northeastCell", "northwestCell", "southeastCell", "southwestCell"};
        for (int counter = 0; counter < cellNames.length; counter++) {
            cells.put(cellNames[counter], level.getCellAdjacentTo(this, directions[counter]));
        }
        return cells;
    }

}
