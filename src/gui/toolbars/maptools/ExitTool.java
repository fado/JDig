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
import data.ExitBuilder;
import gui.CellPanel;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import data.Entity;

public class ExitTool implements MapTool {

    Entity exitEntity;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        exitEntity = cell.getPotentialExitDirection();
        
        if(cell.getEntity().equals(Entity.NO_ENTITY)) {
            if(exitEntity != null) {
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
        doExit(cell, event);
    }

    private void doExit(Cell cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            cell.setEntityType(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cell.setEntityType(cell.getPotentialExitDirection());
            ExitBuilder.build(cell);
        }
    }
    
}
