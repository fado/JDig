package tools;

import gui.AttributesPanel;
import gui.CellPanel;
import gui.MapPanel;
import java.awt.event.MouseEvent;
import main.Cell;

public class SelectionTool implements Tool {

    private CellPanel currentPanel;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
        CellPanel cellPanel = (CellPanel)event.getSource();
        if(cellPanel != currentPanel) {
            getAttributesPanel(cellPanel).saveRoom();
        }
        if(cell.isRoom()) {
            getAttributesPanel(cellPanel).loadRoom(cell.getRoom());
        }
    }
    
    private AttributesPanel getAttributesPanel(CellPanel cellPanel) {
        MapPanel mapPanel = (MapPanel)cellPanel.getParent();
        return mapPanel.getAttributesPanel();
    }
    
}