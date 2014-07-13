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

import gui.CellView;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import data.CellModel;
import main.Entity;
import main.Exit;

public class ExitTool implements Tool {
    
    Exit exitType;
    
    @Override
    public void mouseEntered(CellModel cell, MouseEvent event) {
        CellView cellPanel = (CellView)event.getSource();
        exitType = cell.getPotentialExitType();
        
        if(cell.getEntity().equals(Entity.NO_ENTITY)) {
            if(exitType != null) {
                cellPanel.addImage(exitType.getPath());
                setBorder(cellPanel, exitType);    
            }
        }
    }

    @Override
    public void mouseExited(CellModel cell, MouseEvent event) {
        CellView cellPanel = (CellView)event.getSource();
        if (cell.getEntity().equals(Entity.NO_ENTITY)) {
            cellPanel.removeImage();
            cellPanel.restoreDefaultBorder();
        }
    }

    @Override
    public void mouseClicked(CellModel cell, MouseEvent event) {
        if (SwingUtilities.isRightMouseButton(event)) {
            cell.setEntityType(Entity.NO_ENTITY);
        } else if (SwingUtilities.isLeftMouseButton(event)) {
            cell.setEntityType(Entity.EXIT);
        }
    }
 
    private void setBorder(CellView cellPanel, Exit exit) {
        if (exit == Exit.VERTICAL_EXIT) {
            cellPanel.setVerticalExitBorder();
        } else if (exit == Exit.HORIZONTAL_EXIT) {            
            cellPanel.setHorizontalExitBorder();
        }
    }
    
}
