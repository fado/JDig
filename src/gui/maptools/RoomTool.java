package gui.maptools;

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
import data.Level;
import data.Room;
import gui.CellPanel;
import properties.Images;

import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 * This class controls the addition and removal of Rooms from a Level.
 */
public class RoomTool implements MapTool {

    private Cell firstRoomCell;
    private boolean xEven = false;
    private boolean yEven = false;
    private Level level;
    private Images images = new Images();

    /**
     * Default constructor.
     * @param level The Level on which the tool will be operating.
     */
    public RoomTool(Level level) {
        this.level = level;
    }

    /**
     * Controls what image should be displayed when the user mouses over the Cell.
     * It performs two checks, firstly to ensure that no Entity already exists in
     * the Cell.  If not, it checks whether or not a Room can be placed within the
     * Cell.  This check is based upon the Cell position of the first Room to be
     * added to the level.
     * @param cell
     * @param event The event that originated the method call.
     */
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        // Check that the Cell does not already contain an Entity.
        if (cellPanel.getCell().getEntity() == null) {
            // Check whether or not the Cell Panel can contain a Room.
            if(positionIsValid(cellPanel)) {
                cellPanel.addImage(images.getImagePath("Room"));
                cellPanel.removeBorder();
            } else {
                cellPanel.addImage(images.getImagePath("InvalidRoom"));
                cellPanel.removeBorder();
            }
        }
    }

    /**
     * Removes the image and restores the default border to the Cell, if the
     * Cell contains no Entity.
     * @param cell
     * @param event The event that originated the method call.
     */
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel) event.getSource();
        if (cellPanel.getCell().getEntity() == null) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    /**
     * Controls the behaviour of the tool when the user presses a mouse button.
     * @param cell
     * @param event The event that originated the method call.
     */
    @Override
    public void mousePressed(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel) event.getSource();
        // Delete the Room from the Cell upon right mouse click.
        if (SwingUtilities.isRightMouseButton(event)) {
            new DeleteEntity(cellPanel).execute();
            // If there are no more rooms left, reset room placement restriction.
            if(level.getRooms().size() == 0) {
                firstRoomCell = null;
            }
        // Add a new Room to the Cell upon left mouse click.
        } else if (SwingUtilities.isLeftMouseButton(event) && positionIsValid(cellPanel)) {
            cell.setEntity(new Room(cellPanel));
            // Check if a Room has already been created.
            if(firstRoomCell == null) {
                // Sets the first room, governing where future rooms can be placed.
                setFirstRoomCell(cell);
            }
        }
    }

    /**
     * Prevents rooms from being placed side by side.  Records the position of
     * the first room placed within the level the parity of each of the x and
     * y coordinates.
     * @param cell The new Cell which contains the first Room in the Level.
     */
    private void setFirstRoomCell(Cell cell) {
        this.firstRoomCell = cell;
        if (isEven(cell.X)) {
            xEven = true;
        }
        if (isEven(cell.Y)) {
            yEven = true;
        }
    }

    /**
     * Compares the parity of the coordinates of the CellPanel passed-in with
     * the parity of the coordinates of the Cell which contains the first
     * Room placed in the Level.
     * @param cellPanel The CellPanel whose parity you want to test.
     * @return True if the parity matches, otherwise false.
     */
    private boolean positionIsValid(CellPanel cellPanel) {
        if (firstRoomCell == null) {
            return true;
        }
        return isEven(cellPanel.getX()) == xEven && isEven(cellPanel.getY()) == yEven;
    }

    /**
     * Determines the parity of the passed-in integer.
     * @param coordinate The coordinate under test.
     * @return True if parity is even, otherwise false.
     */
    private boolean isEven(int coordinate) {
        return coordinate % 2 == 0;
    }

}