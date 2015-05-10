package data;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class Cell {

    public final int X;
    public final int Y;
    private final Level level;
    private Entity entity;
    private Color color;

    public Cell(Point point, Level level) {
        this.X = point.x;
        this.Y = point.y;
        this.level = level;
    }

    public void setEntity(Entity entity) { this.entity = entity; }

    public Entity getEntity() { return this.entity; }

    public Level getLevel() { return this.level; }

    public void setColor(Color color) { this.color = color; }

    public Color getColor() { return this.color; }

    public boolean isConnectible() {
        return entity != null && entity instanceof Connectible;
    }

    public boolean isExit() {
        return entity != null && !isConnectible();
    }

    public boolean isInBounds() {
        return this.X >= 0 && this.Y >= 0;
    }

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

    public Map<String, Cell> getAdjacentCells() {
        Map<String, Cell> cells = new HashMap<>();
        Direction[] directions = Direction.values();
        String[] cellNames = {"northCell", "southCell", "eastCell", "westCell",
                "northeastCell", "northwestCell", "southeastCell", "southwestCell"};
        for (int counter = 0; counter < cellNames.length; counter++) {
            cells.put(cellNames[counter], level.getCellAdjacentTo(this, directions[counter]));
        }
        return cells;
    }

}
