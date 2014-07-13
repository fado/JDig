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