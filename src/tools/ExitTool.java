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
import gui.CellView;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import main.Entity;
import main.ExitBuilder;
import main.ExitDirection;

public class ExitTool implements Tool {
    
    ExitDirection exitDirection;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        CellView cellPanel = (CellView)event.getSource();
        exitDirection = cell.getPotentialExitDirection();
        
        if(cell.getEntity().equals(Entity.NO_ENTITY)) {
            if(exitDirection != null) {
                cellPanel.addImage(exitDirection.getPath());
                setBorder(cellPanel, exitDirection);    
            }
        }
    }

    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        CellView cellPanel = (CellView)event.getSource();
        if (cell.getEntity().equals(Entity.NO_ENTITY)) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            cell.setEntityType(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cell.setEntityType(Entity.EXIT);
            ExitBuilder.build(cell);
        }
    }
 
    private void setBorder(CellView cellPanel, ExitDirection exit) {
        if (exit == ExitDirection.VERTICAL_EXIT) {
            cellPanel.setVerticalExitBorder();
        } else if (exit == ExitDirection.HORIZONTAL_EXIT) {            
            cellPanel.setHorizontalExitBorder();
        }
    }
    
}
