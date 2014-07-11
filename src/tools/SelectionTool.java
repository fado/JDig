package tools;

import gui.AttributesPanel;
import gui.CellPanel;
import gui.MapPanel;
import java.awt.event.MouseEvent;
import main.Cell;
import main.entities.Room;

public class SelectionTool implements Tool {
    
    private CellPanel cellPanel;
    private CellPanel currentPanel;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
        this.cellPanel = (CellPanel)event.getSource();
        if(cellPanel != currentPanel) {
            this.saveRoom();
        }
        if(cell.isRoom()) {
            loadRoom(cell.getRoom());
        }
    }
    
    private AttributesPanel getAttributesPanel() {
        MapPanel mapPanel = (MapPanel)cellPanel.getParent();
        return mapPanel.getAttributesPanel();
    }
    
    public void loadRoom(Room room) {
        AttributesPanel attributesPanel = getAttributesPanel();
        attributesPanel.currentRoom = room;
        attributesPanel.roomNameField.setText(room.getName());
    }
    
    public void saveRoom() {
        AttributesPanel attributesPanel = getAttributesPanel();
        if (attributesPanel.currentRoom != null) {
            attributesPanel.currentRoom.setName(attributesPanel.roomNameField.getText());
        }
    }
    
}