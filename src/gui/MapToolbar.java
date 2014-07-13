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
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

    private final String IMAGES = "./resources/images/";
    private final String SELECTION_TOOL_ICON = IMAGES + "selection_tool_16.png";
    private final String ROOM_TOOL_ICON = IMAGES + "room_16.png";
    private final String EXIT_TOOL_ICON = IMAGES + "exit_tool_16.png";
    private final List<ToolListener> listeners;
    private Tool selectedTool;
    private Tool selectionTool;
    
    public MapToolbar() {
        this.setFloatable(false);
        this.listeners = new ArrayList<>();
        // SelectionTool has to preserve some state, so let's just have one.
        this.selectionTool = new SelectionTool();
        this.selectedTool = selectionTool;

        ImageIcon selectionToolIcon = new ImageIcon(SELECTION_TOOL_ICON);
        ImageIcon roomToolIcon = new ImageIcon(ROOM_TOOL_ICON);
        ImageIcon exitToolIcon = new ImageIcon(EXIT_TOOL_ICON);

        final JButton selectionToolButton = new JButton(selectionToolIcon);
        selectionToolButton.setToolTipText("Selection Tool");
        final JButton roomToolButton = new JButton(roomToolIcon);
        roomToolButton.setToolTipText(("Room Tool"));
        final JButton exitToolButton = new JButton(exitToolIcon);
        exitToolButton.setToolTipText("Exit Tool");

        this.add(selectionToolButton);
        this.add(roomToolButton);
        this.add(exitToolButton);
        
        selectionToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSelectedTool(selectionTool);
            }
        });

        roomToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSelectedTool(new RoomTool());
            }
        });

        exitToolButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSelectedTool(new ExitTool());
            }
        });
    }

    public Tool getSelectedTool() {
        return this.selectedTool;
    }

    public void setSelectedTool(Tool tool) {
        this.selectedTool = tool;
        fireToolChanged();
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
