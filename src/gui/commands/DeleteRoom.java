package gui.commands;

import data.*;
import gui.CellPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteRoom {

    public void execute(Cell cell, CellPanel cellPanel) {
        cell.setEntityType(Entity.NO_ENTITY);

        removeDeadExits(cell);

        // Visualise the delete immediately rather than waiting for mouseExited().
        cellPanel.removeImage();
        cellPanel.restoreDefaultBorder();
        if(cellPanel.isSelected()) {
            cellPanel.setDeselected();
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
}
