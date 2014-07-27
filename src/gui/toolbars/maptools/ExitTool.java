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
import data.Direction;
import data.Exit;
import data.ExitBuilder;
import data.Room;
import gui.CellPanel;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.SwingUtilities;
import data.Entity;

public class ExitTool implements MapTool {

    Entity exitEntity;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        exitEntity = cell.getPotentialExitDirection();
        
        if(cell.getEntity().equals(Entity.NO_ENTITY)) {
            if(exitEntity != Entity.NO_ENTITY) {
                cellPanel.addImage(exitEntity.getPath());
                cellPanel.setBorder(exitEntity);
            }
        }
    }

    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        if (cell.getEntity().equals(Entity.NO_ENTITY)) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mousePressed(Cell cell, MouseEvent event) {
        doExit(cell, event);
    }

    @Override
    public void mouseReleased(Cell cell, MouseEvent event) {
        //doExit(cell, event);
    }

    private void doExit(Cell cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            if (cell.isExit()) {
                cell.setEntityType(Entity.NO_ENTITY);
            }
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            Entity potentialEntity = cell.getPotentialExitDirection();
            if (potentialEntity != Entity.NO_ENTITY && !cell.isExit()) {
                cell.setEntityType(cell.getPotentialExitDirection());
                ExitBuilder.build(cell);
            } else {
                CellPanel currentPanel = (CellPanel) event.getSource();
                if (cell.getEntity() == Entity.X_EXIT) {
                    setNewEntity(currentPanel, cell, Entity.FORWARD_DIAGONAL_EXIT);
                } else if (cell.getEntity() == Entity.FORWARD_DIAGONAL_EXIT) {
                    setNewEntity(currentPanel, cell, Entity.BACKWARD_DIAGONAL_EXIT);
                } else if (cell.getEntity() == Entity.BACKWARD_DIAGONAL_EXIT) {
                    setNewEntity(currentPanel, cell, Entity.X_EXIT);
                }
            }
        }
    }

    private void setNewEntity(CellPanel currentPanel, Cell cell, Entity entity) {
        currentPanel.removeImage();
        currentPanel.addImage(entity.getPath());
        cell.setEntityType(entity);

        Map<String, Cell> cells = cell.getAdjacentCells();

        if(entity == Entity.FORWARD_DIAGONAL_EXIT) {
            cells.get("northwestCell").getRoom().removeAllExits();
            cells.get("southeastCell").getRoom().removeAllExits();
            ExitBuilder.buildOnlyForwardDiagonal(cell);
        }
        if(entity == Entity.BACKWARD_DIAGONAL_EXIT) {
            cells.get("northeastCell").getRoom().removeAllExits();
            cells.get("southwestCell").getRoom().removeAllExits();
            ExitBuilder.buildOnlyBackwardDiagonal(cell);
        }
        if(entity == Entity.X_EXIT) {
            ExitBuilder.build(cell);
        }
    }
    
}
