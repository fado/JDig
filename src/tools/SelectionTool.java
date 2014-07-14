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
import gui.AttributesPanel;
import gui.CellView;
import gui.LevelView;
import java.awt.event.MouseEvent;

public class SelectionTool implements Tool {
    
    private CellView currentCellView;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
        currentCellView = (CellView)event.getSource();
        if(cell.isRoom()) {
            getAttributesPanel().load(cell.getRoom());
        }
    }
    
    private AttributesPanel getAttributesPanel() {
        LevelView mapPanel = (LevelView)currentCellView.getParent();
        return mapPanel.getAttributesPanel();
    }
    
}