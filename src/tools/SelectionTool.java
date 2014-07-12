package tools;

import gui.AttributesPanel;
import gui.CellView;
import gui.LevelView;
import java.awt.event.MouseEvent;
import main.CellModel;
import main.RoomModel;

public class SelectionTool implements Tool {
    
    private CellView currentCellPanel;
    private RoomModel currentRoom;
    
    @Override
    public void mouseEntered(CellModel cell, MouseEvent event) {
        
    }
    
    @Override
    public void mouseExited(CellModel cell, MouseEvent event) {
        
    }

    @Override
    public void mouseClicked(CellModel cell, MouseEvent event) {
        this.currentCellPanel = (CellView)event.getSource();
        this.saveRoom();
        if(cell.isRoom()) {
            this.currentRoom = cell.getRoom();
            loadRoom(currentRoom);
        }
    }
    
    private AttributesPanel getAttributesPanel() {
        LevelView mapPanel = (LevelView)currentCellPanel.getParent();
        return mapPanel.getAttributesPanel();
    }
    
    public void loadRoom(RoomModel room) {
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