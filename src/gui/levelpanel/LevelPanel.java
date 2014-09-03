package gui.levelpanel;

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
import gui.JdigComponent;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Collection of CellPanels that make up the main level editor window.
 */
public class LevelPanel extends JPanel implements JdigComponent {

    /**
     * Constructor.
     */
    public LevelPanel() {
        setLayout(new GridBagLayout());
    }

    /**
     * Get the GridBagConstraints for this object.
     */
    public GridBagConstraints getConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;
        return constraints;
    }

    /**
     * Gets the CellPanel at the passed in point.
     * @param point The point at which the CellPanel lies.
     * @return the CellPanel at the passed in point.
     */
    public CellPanel getCellPanel(Point point) {
        CellPanel cellPanel = null;
        for(Component component : this.getComponents()) {
            CellPanel componentPanel = (CellPanel)component;
            if(componentPanel.getPanelX() == point.x && componentPanel.getPanelY() == point.y) {
                cellPanel = componentPanel;
            }
        }
        return cellPanel;
    }

    /**
     * Returns all CellPanels within the LevelPanel.
     * @return all CellPanels within the LevelPanel.
     */
    public List<CellPanel> getAllCellPanels() {
        List<CellPanel> cellPanels = new ArrayList<>();
        for(Component component : this.getComponents()) {
            cellPanels.add((CellPanel)component);
        }
        return cellPanels;
    }
    
}
