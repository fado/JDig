package tools;

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

import data.Cell;
import data.Connection;
import data.ConnectionType;
import data.Direction;
import data.Exit;
import data.Level;
import data.Room;
import gui.CellPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Deletes Entities from Cells in a safe and logical manner.
 */
public class DeletionTool {

    private static CellTool cellTool = new CellTool();

    /**
     * Deletes the Entity in the passed-in CellPanel.
     * @param cellPanel The CellPanel you want to delete the Entity from.
     */
    public void deleteEntity(CellPanel cellPanel) {
        Cell cell = cellPanel.getCell();
        // Check if the Cell contains a room.
        if(cell.isConnectible()) {
            // Unregister the Room from the Level.
            cell.getLevel().unregisterRoom((Room)cell.getEntity());
        }
        removeDeadExits(cell);
        cell.setEntity(null);
        // Visualise the delete immediately rather than waiting for mouse exit.
        cellTool.removeImage(cellPanel);
        cellTool.restoreDefaultBorder(cellPanel);
        if(cellPanel.isSelected()) {
            cellTool.setDeselected(cellPanel);
        }
    }

    /**
     * Remove any dead exits that would result from the deletion of an Entity.
     * @param cell The Cell from which the Entity has been deleted.
     */
    private void removeDeadExits(Cell cell) {
        // Check if we're dealing with a Room.
        if(cell.isConnectible()) {
            List<Room> destinations = getDestinations((Room)cell.getEntity());
            removeExits(destinations, (Room)cell.getEntity());
            removeSurroundingEntities(cell);
        // Check if we're dealing with an Exit.
        } else if(cell.getEntity() != null) {
            Connection connection = (Connection)cell.getEntity();
            ConnectionType connectionType = connection.getConnectionType();
            Level level = cell.getLevel();
            if(connectionType == ConnectionType.HORIZONTAL) {
                Room westRoom = (Room)level.getCellAdjacentTo(cell, Direction.WEST).getEntity();
                removeExit(westRoom, Direction.EAST);
                Room eastRoom = (Room)level.getCellAdjacentTo(cell, Direction.EAST).getEntity();
                removeExit(eastRoom, Direction.WEST);
            }
            if(connectionType == ConnectionType.VERTICAL) {
                Room northRoom = (Room)level.getCellAdjacentTo(cell, Direction.NORTH).getEntity();
                removeExit(northRoom, Direction.SOUTH);
                Room southRoom = (Room)level.getCellAdjacentTo(cell, Direction.SOUTH).getEntity();
                removeExit(southRoom, Direction.NORTH);
            }
            if(connectionType == ConnectionType.FORWARD_DIAGONAL) {
                removeForwardDiagonalExits(level, cell);
            }
            if(connectionType == ConnectionType.BACKWARD_DIAGONAL) {
                removeBackwardDiagonalExits(level, cell);
            }
            if(connectionType == ConnectionType.X) {
                removeForwardDiagonalExits(level, cell);
                removeBackwardDiagonalExits(level, cell);
            }
        }
    }

    /**
     * Removes exits along the forward diagonal axis.
     * @param level
     * @param cell
     */
    private void removeForwardDiagonalExits(Level level, Cell cell) {
        Room southwestRoom = (Room)level.getCellAdjacentTo(cell, Direction.SOUTHWEST).getEntity();
        removeExit(southwestRoom, Direction.NORTHEAST);
        Room northeastRoom = (Room)level.getCellAdjacentTo(cell, Direction.NORTHEAST).getEntity();
        removeExit(northeastRoom, Direction.SOUTHWEST);
    }

    /**
     * Removes exits along the backward diagonal axis.
     * @param level
     * @param cell
     */
    private void removeBackwardDiagonalExits(Level level, Cell cell) {
        Room southeastRoom = (Room)level.getCellAdjacentTo(cell, Direction.SOUTHEAST).getEntity();
        removeExit(southeastRoom, Direction.NORTHWEST);
        Room northwestRoom = (Room)level.getCellAdjacentTo(cell, Direction.NORTHWEST).getEntity();
        removeExit(northwestRoom, Direction.SOUTHEAST);
    }

    /**
     * Removes all Entities from the Cells surrounding the passed-in Cell.
     * @param cell The Cell around which you wish to delete Entities.
     */
    private void removeSurroundingEntities(Cell cell) {
        Map<String, Cell> adjacentCells = cell.getAdjacentCells();
        for(Cell aCell : adjacentCells.values()) {
            // Ensure you're trying to remove a valid Cell.
            if(aCell.X != -1 && aCell.Y != -1) {
                cellTool.removeImage(aCell.getCellPanel());
                cellTool.restoreDefaultBorder(aCell.getCellPanel());
                aCell.setEntity(null);
            }
        }
    }

    /**
     * Removes the Exit in the passed-in Room that has the passed-in Direction.
     * @param room The Room from which you want to delete the Exit.
     * @param direction The Direction of the Exit you wish to remove.
     */
    private void removeExit(Room room, Direction direction) {
        Exit defunctExit = null;
        for(Exit exit : room.getExits()) {
            if (exit.getDirection() == direction) {
                defunctExit = exit;
            }
        }
        room.removeExit(defunctExit);
    }

    /**
     * Iterates through the passed-in list of Rooms and removes any Exits that
     * have the passed-in Room as their destination.
     * @param rooms A list of Rooms.
     * @param destination The Room that is the destination of any Exits to be removed.
     */
    private void removeExits(List<Room> rooms, Room destination) {
        Exit defunctExit = null;
        for(Room room : rooms) {
            for(Exit exit : room.getExits()) {
                if(exit.getDestination().equals(destination)) {
                    defunctExit = exit;
                }
            }
            room.removeExit(defunctExit);
        }
    }

    /**
     * Iterates through all Exits in the passed-in Room and returns a list of
     * all destination Rooms.
     * @param room The Room for which you want a list of destinations.
     * @return all Rooms that are destinations of Exits in the passed-in Room.
     */
    private List<Room> getDestinations(Room room) {
        List<Room> destinations = new ArrayList<>();
        for(Exit exit : room.getExits()) {
            destinations.add(exit.getDestination());
        }
        return destinations;
    }

}
