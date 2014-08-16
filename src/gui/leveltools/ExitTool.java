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
import data.Connection;
import data.ConnectionType;
import data.ExitBuilder;
import data.Entity;
import gui.CellPanel;

import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class ExitTool implements MapTool {

    ConnectionType connectionType;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        Entity entity = cell.getEntity();
        connectionType = cell.getPotentialConnectionType();
        
        if(entity == null) {
            if(connectionType != ConnectionType.NONE) {
                cellPanel.addImage(connectionType.getPath());
                Border border = new Connection(connectionType).getBorder();
                cellPanel.setBorder(border);
            }
        }
    }

    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        Entity entity = cell.getEntity();
        if (entity == null) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mousePressed(Cell cell, MouseEvent event) {
        doExit(cell, event);
    }

    private void doExit(Cell cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            if (cell.isExit()) {
                cell.setEntity(null);
            }
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            ConnectionType connectionType = cell.getPotentialConnectionType();
            if (connectionType != ConnectionType.NONE && !cell.isExit()) {
                cell.setEntity(new Connection(cell.getPotentialConnectionType()));
                ExitBuilder.build(cell);
            }
        }
    }
    
}
