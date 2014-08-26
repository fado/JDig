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
import gui.CellPanel;
import gui.InfoPanel;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Tool for selecting CellPanels within a Level.
 */
public class SelectionTool implements LevelTool {

    private InfoPanel infoPanel;
    private List<CellPanel> selectedPanels = new ArrayList<>();
    
    public SelectionTool(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    /**
     * Handles the behaviour of the tool on the mouse entering the CellPanel.
     * @param event The event originating the call.
     */
    @Override
    public void mouseEntered(MouseEvent event) {
        // No behaviour to be executed.
    }

    /**
     * Handles the behaviour of the tool on mouse exiting the CellPanel.
     * @param event The event originating the call.
     */
    @Override
    public void mouseExited(MouseEvent event) {
        // No behaviour to be executed.
    }

    /**
     * Handles the behaviour of the tool when a mouse button is pressed.
     * @param event The event originating the call.
     */
    @Override
    public void mousePressed(MouseEvent event) {
        CellPanel cellPanel = (CellPanel) event.getSource();
        Cell cell = cellPanel.getCell();
        doSelection(cell, event);
    }

    /**
     * Handles the selection of CellPanels.  Checks to make sure that the
     * CellPanel being selected contains a Room, otherwise deselects all
     * currently selected panels.  If it does contain a Room, it then check
     * to see if shift is down.  If not, it deselects any previously
     * selected CellPanels before selecting the panel that is the event
     * source.  If shift is down, CellPanels are continuously added to the
     * list of selected panels.
     * @param cell
     * @param event The event originating the call.
     */
    private void doSelection(Cell cell, MouseEvent event) {
        if (cell.isConnectible()) {
            // Unless shift is down, deselect selected panels.
            if(!event.isShiftDown()) {
                doDeselect();
            }
            CellPanel lastSelectedPanel = (CellPanel) event.getSource();
            selectedPanels.add(lastSelectedPanel);
            lastSelectedPanel.select();
            infoPanel.load(lastSelectedPanel);
        } else {
            doDeselect();
        }
    }

    /**
     * Deselects any currently selected panels and unloads them from the InfoPanel.
     */
    private void doDeselect() {
        if (!selectedPanels.isEmpty()) {
            for(CellPanel panel : selectedPanels) {
                panel.deselect();
            }
            infoPanel.unload();
        }
    }

}
