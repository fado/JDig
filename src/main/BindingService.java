package main;

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
import data.Street;
import gui.levelpanel.CellPanel;
import properties.Images;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Binds Cells to CellPanels.
 */
public class BindingService {

    private Map<Cell, CellPanel> mappingCells = new HashMap<>();
    private Level level;

    public BindingService(Level level) {
        this.level = level;
    }

    /**
     * Binds a CellPanel to the passed in Cell.
     * @param cell The Cell to which you want to bind a CellPanel.
     */
    public void bindToCellPanel(Cell cell) {
        if(!mappingCells.containsKey(cell)) {
            mappingCells.put(cell, getCellPanel(cell));
        }
    }

    /**
     * Returns a CellPanel for the passed-in Cell.  If the Cell
     * contains an entity, it passes it along to the restoreCellPanel
     * method.  If not, it simply returns a new CellPanel.
     * @param cell The Cell for which you want a CellPanel.
     * @return a CellPanel for the passed-in Cell.
     */
    private CellPanel getCellPanel(Cell cell) {
        CellPanel cellPanel;
        if(cell.getEntity() != null) {
            cellPanel = restoreCellPanel(cell);
        } else {
            cellPanel = new CellPanel(cell.X, cell.Y);
        }
        return cellPanel;
    }

    /**
     * Returns a CellPanel with an appearance appropriate for the
     * contents of the passed-in Cell.
     * @param cell The Cell corresponding to the CellPanel.
     * @return the restored CellPanel.
     */
    private CellPanel restoreCellPanel(Cell cell) {
        CellPanel cellPanel = new CellPanel(cell.X, cell.Y);

        if(cell.isConnectible()) {
            Images images = new Images();
            cellPanel.addImage(images.getImagePath("Room"));
            if(cell.getColor() != null) {
                cellPanel.setBackground(cell.getColor());
            }
            cellPanel.removeBorder();
        }
        if(cell.isExit()) {
            cellPanel.addImage(cell.getEntity().getNormalImage());
            cellPanel.setBorder(cell.getEntity());
        }
        return cellPanel;
    }

    /**
     * Returns the CellPanel bound to the Cell.
     * @param cell The Cell for which you want the bound CellPanel.
     * @return the CellPanel bound to the Cell.
     */
    public CellPanel getBoundCellPanel(Cell cell) {
        if(mappingCells.get(cell) != null) {
            return mappingCells.get(cell);
        }
        return null;
    }

    /**
     * Returns the Cell bound to the CellPanel.
     * @param cellPanel The CellPanel for which you want the bound Cell.
     * @return the Cell bound to the CellPanel.
     */
    public Cell getBoundCell(CellPanel cellPanel) {
        for(Map.Entry<Cell, CellPanel> tuple : mappingCells.entrySet()) {
            if(tuple.getValue() == cellPanel) {
                return tuple.getKey();
            }
        }
        return null;
    }

    /**
     * Returns all Streets in the Level.
     * @return all Streets in the Level.
     */
    public List<Street> getStreets() {
        return this.level.getStreets();
    }

    /**
     * Adds a Street to the currently loaded Level.
     * @param street The Street to be added.
     */
    public void addStreet(Street street) {
        level.addStreet(street);
    }

    public void removeStreet(Street street) {
        level.removeStreet(street);
    }

    public Street getStreet(String streetName) {
        return level.getStreet(streetName);
    }

}
