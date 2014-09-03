package gui.menubar;

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

import gui.levelpanel.CellPanel;
import main.ApplicationFactory;
import tools.DeletionTool;
import tools.SelectionTool;
import java.util.List;

/**
 * Command for deleting entities from the level.
 */
public class DeleteCommand extends Command {

    private SelectionTool selectionTool;

    /**
     * Constructor.
     * @param selectionTool Currently loaded SelectionTool.
     */
    public DeleteCommand(SelectionTool selectionTool) {
        this.selectionTool = selectionTool;
    }

    /**
     * Iterates through all currently selected panels and passes each of
     * them to the deletion tool.
     */
    @Override
    public void execute() {
        List<CellPanel> selectedPanels = selectionTool.getSelectedPanels();
        DeletionTool deletionTool = ApplicationFactory.INSTANCE.getDeletionTool();
        for(CellPanel cellPanel : selectedPanels) {
            deletionTool.deleteEntity(cellPanel);
        }
    }

}
