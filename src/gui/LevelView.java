package gui;

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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import data.Cell;
import data.Level;

public class LevelView extends JPanel {
    
    private AttributesPanel attributesPanel;
    
    public LevelView(Level level, MapToolbar toolbar, AttributesPanel attributesPanel) {
        this.attributesPanel = attributesPanel;
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        for(Cell cell : level.getAllCells()) {
            constraints.gridx = cell.X;
            constraints.gridy = cell.Y;
            CellView cellUi = new CellView(cell);
            add(cellUi, constraints);
            toolbar.addToolListener(cellUi);
        }
    }
    
    public AttributesPanel getAttributesPanel() {
        return this.attributesPanel;
    }
    
}
