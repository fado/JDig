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

import tools.CellTool;
import tools.ConnectionTool;
import tools.DeletionTool;
import tools.ExitBuilder;
import tools.LevelTool;
import tools.LevelToolEvent;
import tools.LevelToolListener;
import tools.RoomTool;
import tools.SelectionTool;

/**
 * Toolbar for the level editor.
 */
public class LevelToolbar extends JToolBar {

    private final List<LevelToolListener> listeners = new ArrayList<>();
    private LevelTool selectedLevelTool;
    private LevelTool selectionTool;
    private LevelTool roomTool;
    private LevelTool exitTool;
    private final InfoPanel infoPanel;
    
    public LevelToolbar(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
        setDefaultProperties();
        
        this.add(ToolbarButtonBuilder.build("SelectionTool",
                getToolChangeAction(selectionTool)));
        this.add(ToolbarButtonBuilder.build("RoomTool",
                getToolChangeAction(roomTool)));
        this.add(ToolbarButtonBuilder.build("ExitTool",
                getToolChangeAction(exitTool)));
    }

    /**
     * Sets up the default properties for the tool bar, including the associated
     * tools.
     */
    private void setDefaultProperties() {
        CellTool cellTool = new CellTool();
        DeletionTool deletionTool = new DeletionTool(cellTool);
        ExitBuilder exitBuilder = new ExitBuilder();
        this.setFloatable(false);
        this.selectionTool = new SelectionTool(infoPanel);
        this.roomTool = new RoomTool(infoPanel.getLevel(), cellTool, deletionTool);
        this.exitTool = new ConnectionTool(cellTool, deletionTool, exitBuilder);
    }

    /**
     * Sets the default LevelTool to the SelectionTool setup in the default
     * properties.
     */
    public void setDefaultLevelTool() {
        setSelectedLevelTool(selectionTool);
    }

    /**
     * Returns the selected LevelTool.
     * @return the selected LevelTool.
     */
    public LevelTool getSelectedLevelTool() {
        return this.selectedLevelTool;
    }

    /**
     * Sets the selected LevelTool.
     * @param levelTool The selected LevelTool.
     */
    public void setSelectedLevelTool(LevelTool levelTool) {
        this.selectedLevelTool = levelTool;
        fireToolChanged();
    }

    /**
     * Returns the SelectionTool.
     * @return the SelectionTool.
     */
    public SelectionTool getSelectionTool() {
        return (SelectionTool)this.selectionTool;
    }

    /**
     * Returns the RoomTool.
     * @return the RoomTool.
     */
    public RoomTool getRoomTool() {
        return (RoomTool)this.roomTool;
    }

    /**
     * Returns an ActionListener that sets the currently selected LevelTool
     * in the LevelToolbar to the passed-in LevelTool.
     * @param levelTool The LevelTool associated with the button.
     * @return An ActionListener that will set the appropriate LevelTool for the
     * button pressed.
     */
    private ActionListener getToolChangeAction(final LevelTool levelTool) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSelectedLevelTool(levelTool);
            }
        };
    }

    /**
     * Adds a ToolListener to the LevelToolbar.
     * @param levelToolListener The ToolListener to add.
     */
    public void addToolListener(LevelToolListener levelToolListener) {
        listeners.add(levelToolListener);
    }

    /**
     * Fires whenever the selected tool is changed.
     */
    protected void fireToolChanged() {
        LevelToolEvent levelToolEvent = new LevelToolEvent(this, getSelectedLevelTool());
        for (LevelToolListener listener : listeners) {
            listener.toolChanged(levelToolEvent);
        }
    }

}
