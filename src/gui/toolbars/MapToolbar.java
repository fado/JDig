package gui.toolbars;

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

import gui.infopanel.InfoPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JToolBar;

import gui.toolbars.maptools.ExitTool;
import gui.toolbars.maptools.MapTool;
import gui.toolbars.maptools.MapToolEvent;
import gui.toolbars.maptools.MapToolListener;
import gui.toolbars.maptools.RoomTool;
import gui.toolbars.maptools.SelectionTool;

/**
 * The MapEditorToolbar class specifies the tool bar for the map editor.
 *
 * @author Administrator
 */
public class MapToolbar extends JToolBar {

    private final List<MapToolListener> listeners = new ArrayList<>();
    private MapTool selectedMapTool;
    private MapTool selectionTool;
    private MapTool roomTool;
    private MapTool exitTool;
    private final InfoPanel infoPanel;
    
    public MapToolbar(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        setDefaultProperties();
        
        this.add(ToolbarButtonBuilder.build("SelectionTool", getToolChangeAction(selectionTool)));
        this.add(ToolbarButtonBuilder.build("RoomTool", getToolChangeAction(roomTool)));
        this.add(ToolbarButtonBuilder.build("ExitTool", getToolChangeAction(exitTool)));
    }

    private void setDefaultProperties() {
        this.setFloatable(false);
        this.selectionTool = new SelectionTool(infoPanel);
        this.roomTool = new RoomTool(infoPanel.getLevel());
        this.exitTool = new ExitTool();
    }
    
    public void setDefaultSelectionTool() {
        setSelectedMapTool(selectionTool);
    }
    
    public MapTool getSelectedMapTool() {
        return this.selectedMapTool;
    }

    public void setSelectedMapTool(MapTool mapTool) {
        this.selectedMapTool = mapTool;
        fireToolChanged();
    }

    public SelectionTool getSelectionTool() {
        return (SelectionTool)this.selectionTool;
    }

    public RoomTool getRoomTool() {
        return (RoomTool)this.roomTool;
    }
    
    private ActionListener getToolChangeAction(final MapTool mapTool) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSelectedMapTool(mapTool);
            }
        };
        return listener;
    }
    
    public void addToolListener(MapToolListener obj) {
        listeners.add(obj);
    }

    protected void fireToolChanged() {
        MapToolEvent mapToolEvent = new MapToolEvent(this, getSelectedMapTool());
        for (MapToolListener listener : listeners) {
            listener.toolChanged(mapToolEvent);
        }
    }

}
