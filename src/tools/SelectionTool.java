package tools;

import gui.AttributesPanel;
import gui.CellPanel;
import gui.MapPanel;
import java.awt.event.MouseEvent;
import main.Cell;
import main.entities.Room;

public class SelectionTool implements Tool {
    
    private CellPanel cellPanel;
    private Room currentRoom;
    
    @Override
    public void mouseEntered(Cell cell, MouseEvent event) {
        
    }
    
    @Override
    public void mouseExited(Cell cell, MouseEvent event) {
        
    }

    @Override
    public void mouseClicked(Cell cell, MouseEvent event) {
        this.cellPanel = (CellPanel)event.getSource();
        this.saveRoom();
        if(cell.isRoom()) {
            this.currentRoom = cell.getRoom();
            loadRoom(currentRoom);
        }
    }
    
    private AttributesPanel getAttributesPanel() {
        MapPanel mapPanel = (MapPanel)cellPanel.getParent();
        return mapPanel.getAttributesPanel();
    }
    
    public void loadRoom(Room room) {
        getAttributesPanel()
                .roomNameField.setText(room.getName());
    }
    
    public void saveRoom() {
        AttributesPanel attributesPanel = getAttributesPanel();
        if (this.currentRoom != null) {
            this.currentRoom
                    .setName(attributesPanel.roomNameField.getText());
        }
    }
    
}