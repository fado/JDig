package gui.toolbars.maptools;

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
import data.Entity;
import data.Level;
import data.Room;
import gui.CellPanel;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

public class RoomTool implements MapTool {

    private final String ROOM_IMAGE = "./resources/images/room.png";
    private final String INVALID_ROOM_IMAGE = "./resources/images/invalid_room.png";
    private Cell firstRoomCell;
    private boolean xEven = false;
    private boolean yEven = false;
    private Level level;

    public RoomTool(Level level) {
        this.level = level;
    }
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        if (cell.getEntity().equals(Entity.NO_ENTITY)) {
            if(positionIsValid(cellPanel)) {
                cellPanel.addImage(ROOM_IMAGE);
                cellPanel.removeBorder();
            } else {
                cellPanel.addImage(INVALID_ROOM_IMAGE);
                cellPanel.removeBorder();
            }
        }
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel) event.getSource();
        if (cell.getEntity().equals(Entity.NO_ENTITY)) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mousePressed(Cell cell, MouseEvent event) {
        doRoom(cell, event);
    }

    @Override
    public void mouseReleased(Cell cell, MouseEvent event) {
        doRoom(cell, event);
    }

    private void doRoom(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel) event.getSource();
        if (SwingUtilities.isRightMouseButton(event)) {
            deleteRoom(cell, cellPanel);
        } else if (SwingUtilities.isLeftMouseButton(event) && positionIsValid(cellPanel)) {
            cell.setEntityType(Entity.ROOM);
            cell.setRoom(new Room());
            if(firstRoomCell == null) {
                setFirstRoomCell(cell);
            }
        }
    }

    private void deleteRoom(Cell cell, CellPanel cellPanel) {
        cell.setEntityType(Entity.NO_ENTITY);
        removeDeadExits(cell);
        // Visualise the delete immediately rather than waiting for mouseExited().
        cellPanel.removeImage();
        cellPanel.restoreDefaultBorder();
        if(cellPanel.isSelected()) {
            cellPanel.setDeselected();
        }
        if(level.getRooms().isEmpty()) {
            firstRoomCell = null;
        }
    }

    private void removeDeadExits(Cell cell) {
        List<Room> destinations = new ArrayList<>();
        for(data.Exit exit : cell.getRoom().getExits()) {
            destinations.add(exit.getDestination());
        }
        data.Exit exitToRemove = null;
        for(Room room : destinations) {
            for(data.Exit exit : room.getExits()) {
                if(exit.getDestination().equals(cell.getRoom())) {
                    exitToRemove = exit;
                }
            }
            room.removeExit(exitToRemove);
        }
        Map<String, Cell> adjacentCells = cell.getAdjacentCells();
        for(Cell aCell : adjacentCells.values()) {
            aCell.getCellPanel().removeImage();
            aCell.getCellPanel().restoreDefaultBorder();
            aCell.setEntityType(Entity.NO_ENTITY);
        }
    }

    private boolean positionIsValid(CellPanel cellPanel) {
        if (firstRoomCell == null) {
            return true;
        }
        return isEven(cellPanel.getX()) == xEven && isEven(cellPanel.getY()) == yEven;
    }

    private void setFirstRoomCell(Cell cell) {
        this.firstRoomCell = cell;
        if (isEven(cell.X)) {
            xEven = true;
        }
        if (isEven(cell.Y)) {
            yEven = true;
        }
    }

    private boolean isEven(int coordinate) {
        return coordinate % 2 == 0;
    }
}
