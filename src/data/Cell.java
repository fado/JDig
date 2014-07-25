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

import gui.CellPanel;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Cell {

    public final int X;
    public final int Y;
    private final Level parentLevel;
    private Entity currentEntity;
    private Room room;
    private CellPanel cellPanel;

    public Cell(Point point, Level map) {
        this.X = point.x;
        this.Y = point.y;
        this.parentLevel = map;
        this.currentEntity = Entity.NO_ENTITY;
    }

    public Level getParent() {
        return this.parentLevel;
    }

    public void setEntityType(Entity entity) {
        if (entity == Entity.ROOM) {
            room = new Room();
        }
        this.currentEntity = entity;
    }
    
    public Entity getEntity() {
        return this.currentEntity;
    }

    public Room getRoom() {
        return this.room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }

    public void setCellPanel(CellPanel cellPanel) {
        this.cellPanel = cellPanel;
    }

    public CellPanel getCellPanel() {
        return this.cellPanel;
    }

    public boolean isRoom() {
        return currentEntity == Entity.ROOM && this.isInBounds();
    }

    private boolean isInBounds() {
        return this.X >= 0 && this.Y >= 0;
    }

    public boolean isExit() {
        return currentEntity == Entity.VERTICAL_EXIT ||
                currentEntity == Entity.HORIZONTAL_EXIT ||
                currentEntity == Entity.FORWARD_DIAGONAL_EXIT ||
                currentEntity == Entity.BACKWARD_DIAGONAL_EXIT ||
                currentEntity == Entity.X_EXIT;
    }

    /**
     * Determines the potential Entity that could exist in the Cell, given its
     * surroundings.
     *
     * @return - The potential Entity that could exist in the Cell.
     */
    public Entity getPotentialExitDirection() {
        Map <String, Cell> cells = getAdjacentCells();
        // Check for rooms adjacent horizontally.
        if (cells.get("westCell").isRoom() && cells.get("eastCell").isRoom()) {
            return Entity.HORIZONTAL_EXIT;
        }
        // Check for rooms adjacent vertically.
        if (cells.get("northCell").isRoom() && cells.get("southCell").isRoom()) {
            return Entity.VERTICAL_EXIT;
        }
        // Check for rooms across both diagonal axis.
        if (cells.get("northwestCell").isRoom() && cells.get("northeastCell").isRoom()
                && cells.get("southwestCell").isRoom() && cells.get("southeastCell").isRoom()) {
            return Entity.X_EXIT;
        } 
        // Check for rooms on the southwest/northeast axis.
        else if (cells.get("southwestCell").isRoom() && cells.get("northeastCell").isRoom()) {
            return Entity.FORWARD_DIAGONAL_EXIT;
        } 
        // Check for rooms on the southeast/northwest axis.
        else if (cells.get("southeastCell").isRoom() && cells.get("northwestCell").isRoom()) {
            return Entity.BACKWARD_DIAGONAL_EXIT;
        }
        return null;
    }
    
    public Map<String, Cell> getAdjacentCells() {
        Map <String, Cell> cells = new HashMap<>();
        cells.put("westCell", parentLevel.getCellAdjacentTo(this, Direction.WEST));
        cells.put("eastCell", parentLevel.getCellAdjacentTo(this, Direction.EAST));
        cells.put("northCell", parentLevel.getCellAdjacentTo(this, Direction.NORTH));
        cells.put("southCell", parentLevel.getCellAdjacentTo(this, Direction.SOUTH));
        cells.put("northwestCell", parentLevel.getCellAdjacentTo(this, Direction.NORTHWEST));
        cells.put("northeastCell", parentLevel.getCellAdjacentTo(this, Direction.NORTHEAST));
        cells.put("southwestCell", parentLevel.getCellAdjacentTo(this, Direction.SOUTHWEST));
        cells.put("southeastCell", parentLevel.getCellAdjacentTo(this, Direction.SOUTHEAST));
        return cells;
    }

}
