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
import data.Entity;
import gui.levelpanel.CellPanel;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 * This class controls the addition and removal of Connections from a Level.
 */
public class ConnectionTool implements LevelTool {

    ConnectionType connectionType;
    private DeletionTool deletionTool;
    private ExitBuilder exitBuilder;

    /**
     * Constructor.
     * @param deletionTool A DeletionTool.
     * @param exitBuilder An ExitBuilder.
     */
    public ConnectionTool(DeletionTool deletionTool, ExitBuilder exitBuilder) {
        this.deletionTool = deletionTool;
        this.exitBuilder = exitBuilder;
    }

    /**
     * Controls what image should be displayed when the user mouses over the Cell.
     * @param event The event that originated the method call.
     */
    @Override
    public void mouseEntered(MouseEvent event) {
        CellPanel cellPanel = (CellPanel) event.getSource();
        Cell cell = cellPanel.getCell();
        Entity entity = cell.getEntity();
        connectionType = cell.getPotentialConnectionType();

        // Check there isn't an Entity in the Cell already.
        if (entity == null && connectionType != ConnectionType.NONE) {
            cellPanel.addImage(connectionType.getPath());
            Border border = new Connection(connectionType).getBorder();
            cellPanel.setBorder(border);
        }
    }

    /**
     * Removes the image and restores the default border to the Cell, if the Cell
     * contains no Entity.
     * @param event The event that originated the method call.
     */
    @Override
    public void mouseExited(MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        Cell cell = cellPanel.getCell();
        Entity entity = cell.getEntity();
        if (entity == null) {
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
        if (SwingUtilities.isRightMouseButton(event)) {
            deleteConnection(cellPanel);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            createExits(cellPanel);
        }
    }

    /**
     * Delete the Connection from the passed-in CellPanel.
     * @param cellPanel The CellPanel you're deleting the Connection from.
     */
    private void deleteConnection(CellPanel cellPanel) {
        deletionTool.deleteEntity(cellPanel);
    }

    /**
     * Creates a new Connection in the Cell and instructs the ExitBuilder to
     * build appropriate Exits in the surrounding rooms.
     * @param cellPanel The CellPanel you're adding the Connection to.
     */
    private void createExits(CellPanel cellPanel) {
        Cell cell = cellPanel.getCell();
        ConnectionType connectionType = cell.getPotentialConnectionType();
        if (connectionType != ConnectionType.NONE && !cell.isExit()) {
            cell.setEntity(new Connection(cell.getPotentialConnectionType()));
            exitBuilder.build(cell);
        }
    }
    
}
