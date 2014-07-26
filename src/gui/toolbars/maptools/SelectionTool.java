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
import gui.CellPanel;
import gui.infopanel.InfoPanel;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class SelectionTool implements MapTool {

    private InfoPanel infoPanel;
    private CellPanel lastPanel;
    private List<CellPanel> selectedPanels = new ArrayList<>();
    private CellPanel selectedPanel;
    
    public SelectionTool(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        
    }

    @Override
    public void mousePressed(Cell cell, MouseEvent event) {
        doSelection(cell, event);
    }

    @Override
    public void mouseReleased(Cell cell, MouseEvent event) {
        //doSelection(cell, event);
    }

    private void doSelection(Cell cell, MouseEvent event) {
        if (event.isShiftDown()) {
            shiftDownSelection(cell, event);
        } else if (cell.isRoom()) {
            normalSelection(cell, event);
        }
    }

    private void shiftDownSelection(Cell cell, MouseEvent event) {
        if (selectedPanels.isEmpty()) {
            selectedPanels.add(selectedPanel);
        }
        CellPanel currentPanel = (CellPanel) event.getSource();
        if (cell.isRoom()) {
            selectedPanels.add(currentPanel);
            currentPanel.setSelected();
        }
        infoPanel.loadMultiple(currentPanel);
    }

    private void normalSelection(Cell cell, MouseEvent event) {
        // Clear previously selected panels.
        clearSelectedPanels();
        infoPanel.unloadMultiple();

        if (selectedPanel != null) {
            lastPanel = selectedPanel;
            lastPanel.setDeselected();
        }
        selectedPanel = (CellPanel) event.getSource();
        selectedPanel.setSelected();
        infoPanel.load(selectedPanel);
    }

    public List<CellPanel> getSelectedPanels() {
        return this.selectedPanels;
    }

    public CellPanel getSelectedPanel() {
        return this.selectedPanel;
    }

    public void clearSelectedPanels() {
        for(CellPanel panel : selectedPanels) {
            panel.setDeselected();
        }
        selectedPanels.clear();
    }
}
