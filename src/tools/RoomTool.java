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
import data.Level;
import data.Room;
import gui.JdigDialog;
import gui.levelpanel.CellPanel;
import main.BindingService;
import properties.Images;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * This class controls the addition and removal of Rooms from a Level.
 */
public class RoomTool implements LevelTool {

    private Level level;
    private Images images = new Images();
    private DeletionTool deletionTool;
    private PlacementRestriction placementRestriction;
    private BindingService bindingService;
    private JdigDialog jdigDialog;

    /**
     * Default constructor.
     * @param level The Level on which the tool will be operating.
     */
    public RoomTool(Level level,
                    DeletionTool deletionTool,
                    PlacementRestriction placementRestriction,
                    BindingService bindingService,
                    JdigDialog jdigDialog) {
        this.level = level;
        this.deletionTool = deletionTool;
        this.placementRestriction = placementRestriction;
        this.bindingService = bindingService;
        this.jdigDialog = jdigDialog;
    }

    /**
     * Controls what image should be displayed when the user mouses over the Cell.
     * It performs two checks, firstly to ensure that no Entity already exists in
     * the Cell.  If not, it checks whether or not a Room can be placed within the
     * Cell.  This check is based upon the Cell position of the first Room to be
     * added to the level.
     * @param event The event that originated the method call.
     */
    @Override
    public void mouseEntered(MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        //Cell cell = cellPanel.getCell();
        Cell cell = bindingService.getBoundCell(cellPanel);
        // Check that the Cell does not already contain an Entity.
        if (cell.getEntity() == null) {
            // Check whether or not the Cell Panel can contain a Room.
            if(placementRestriction.positionIsValid(cell)) {
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
     * @param event The event that originated the method call.
     */
    @Override
    public void mouseExited(MouseEvent event) {
        CellPanel cellPanel = (CellPanel) event.getSource();
        Cell cell = bindingService.getBoundCell(cellPanel);
        if (cell.getEntity() == null) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    /**
     * Controls the behaviour of the tool when the user presses a mouse button.
     * @param event The event that originated the method call.
     */
    @Override
    public void mousePressed(MouseEvent event) {
        CellPanel cellPanel = (CellPanel) event.getSource();
        //Cell cell = cellPanel.getCell();
        Cell cell = bindingService.getBoundCell(cellPanel);
        // Delete the Room from the Cell upon right mouse click.
        if (SwingUtilities.isRightMouseButton(event)) {
            deletionTool.deleteEntity(cellPanel);
            // If there are no more rooms left, reset room placement restriction.
            if(level.getRooms().isEmpty()) {
                placementRestriction.resetPlacementRestriction();
            }
        // Add a new Room to the Cell upon left mouse click.
        } else if (SwingUtilities.isLeftMouseButton(event)
                && placementRestriction.positionIsValid(cell)) {
            placeRoom(cell, cellPanel);
        }
    }

    /**
     * Controls the placement of rooms. First ensures that you're not
     * overwriting.
     * @param cell The Cell in which you're placing the Room.
     * @param cellPanel The CellPanel representing the Cell.
     */
    private void placeRoom(Cell cell, CellPanel cellPanel) {
        if(cell.getEntity() != null) {
            overwriteRoom(cell, cellPanel);
        } else {
            Room room = new Room(cell);
            cell.setEntity(room);
            level.registerRoom(room);
            // Check if a Room has already been created.
            if(!placementRestriction.isSet()) {
                // Sets the first room, governing where future rooms can be placed.
                placementRestriction.setPlacementRestriction(cell);
            }
        }
    }

    /**
     * Overwrites the passed in Cell and CellPanel with a new room.
     * @param cell The underlying Cell.
     * @param cellPanel The CellPanel representing the Cell.
     */
    private void overwriteRoom(Cell cell, CellPanel cellPanel) {
        int option = jdigDialog.showDialog();
        if(option == JOptionPane.YES_OPTION) {
            deletionTool.deleteEntity(cellPanel);
            cellPanel.addImage(images.getImagePath("Room"));
            cellPanel.removeBorder();
            placeRoom(cell, cellPanel);
        }
    }

}
