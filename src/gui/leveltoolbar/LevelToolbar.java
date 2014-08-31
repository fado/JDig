package gui.leveltoolbar;

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

import gui.JdigComponent;
import gui.ToolbarButtonBuilder;
import tools.*;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JToolBar;

/**
 * Toolbar for the level editor.
 */
public class LevelToolbar extends JToolBar implements JdigComponent {

    private final List<LevelToolListener> listeners = new ArrayList<>();
    private LevelTool selectedLevelTool, selectionTool, roomTool, connectionTool;
    
    public LevelToolbar(SelectionTool selectionTool, RoomTool roomTool,
                        ConnectionTool connectionTool) {
        this.selectionTool = selectionTool;
        this.roomTool = roomTool;
        this.connectionTool = connectionTool;
        setDefaultProperties();
        
        this.add(ToolbarButtonBuilder.build("SelectionTool",
                getToolChangeAction(this.selectionTool)));
        this.add(ToolbarButtonBuilder.build("RoomTool",
                getToolChangeAction(this.roomTool)));
        this.add(ToolbarButtonBuilder.build("ExitTool",
                getToolChangeAction(this.connectionTool)));
    }

    /**
     * Sets up the default properties for the tool bar, including the associated
     * tools.
     */
    private void setDefaultProperties() {
        this.setFloatable(false);
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
     * Get the GridBagConstraints for this object.
     */
    public GridBagConstraints getConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        return constraints;
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
