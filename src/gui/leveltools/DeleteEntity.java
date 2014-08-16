package gui.leveltools;

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
import data.Exit;
import data.Room;
import gui.CellPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteEntity {

    private CellPanel cellPanel;

    public DeleteEntity(CellPanel cellPanel) {
        this.cellPanel = cellPanel;
    }

    public void execute() {
        Cell cell = cellPanel.getCell();
        if(cell.isConnectible()) {
            removeDeadExits(cell);
        }
        cell.setEntity(null);
        // Visualise the delete immediately rather than waiting for mouse exit.
        cellPanel.removeImage();
        cellPanel.restoreDefaultBorder();
        if(cellPanel.isSelected()) {
            cellPanel.setDeselected();
        }
    }

    private void removeDeadExits(Cell cell) {
        List<Room> destinations = new ArrayList<>();
        Room entity = (Room)cell.getEntity();
        for(Exit exit : entity.getExits()) {
            destinations.add(exit.getDestination());
        }
        Exit exitToRemove = null;
        for(Room room : destinations) {
            for(Exit exit : room.getExits()) {
                if(exit.getDestination().equals(cell.getEntity())) {
                    exitToRemove = exit;
                }
            }
            room.removeExit(exitToRemove);
        }
        Map<String, Cell> adjacentCells = cell.getAdjacentCells();
        for(Cell aCell : adjacentCells.values()) {
            if(aCell.X != -1 && aCell.Y != -1) {
                aCell.getCellPanel().removeImage();
                aCell.getCellPanel().restoreDefaultBorder();
                aCell.setEntity(null);
            }
        }
    }

}
