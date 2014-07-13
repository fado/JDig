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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JToolBar;
import tools.SelectionTool;
import tools.ExitTool;
import tools.RoomTool;
import tools.Tool;
import tools.ToolEvent;
import tools.ToolListener;

/**
 * The MapEditorToolbar class specifies the tool bar for the map editor.
 *
 * @author Administrator
 */
public class MapToolbar extends JToolBar {

    private final List<ToolListener> listeners = new ArrayList<>();
    private Tool selectedTool;
    private Tool selectionTool;
    private Tool roomTool;
    private Tool exitTool;
    
    public MapToolbar() {
        setDefaultProperties();
        setDefaultSelectionTool();
        
        this.add(ToolbarButton.build("SelectionTool", getToolChangeAction(selectionTool)));
        this.add(ToolbarButton.build("RoomTool", getToolChangeAction(roomTool)));
        this.add(ToolbarButton.build("ExitTool", getToolChangeAction(exitTool)));
    }

    private void setDefaultProperties() {
        this.setFloatable(false);
        this.selectionTool = new SelectionTool();
        this.roomTool = new RoomTool();
        this.exitTool = new ExitTool();
    }
    
    private void setDefaultSelectionTool() {
        this.selectedTool = selectionTool;
    }
    
    public Tool getSelectedTool() {
        return this.selectedTool;
    }

    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
        fireToolChanged();
    }
    
    private ActionListener getToolChangeAction(final Tool tool) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSelectedTool(tool);
            }
        };
        return listener;
    }
    
    public void addToolListener(ToolListener obj) {
        listeners.add(obj);
    }

    protected void fireToolChanged() {
        ToolEvent toolEvent = new ToolEvent(this, getSelectedTool());
        for (ToolListener listener : listeners) {
            listener.toolChanged(toolEvent);
        }
    }

}
