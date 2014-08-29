package gui.levelpanel;

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

import data.Cell;
import data.Level;
import gui.infopanel.InfoPanel;
import gui.leveltoolbar.LevelToolbar;
import org.junit.Before;
import org.junit.Test;
import tools.ConnectionTool;
import tools.DeletionTool;
import tools.ExitBuilder;
import tools.PlacementRestriction;
import tools.RoomTool;
import tools.SelectionTool;

import java.awt.Point;
import static org.junit.Assert.assertNotNull;

public class LevelPanelTest {

    Level level;
    LevelToolbar toolbar;
    LevelPanel levelPanel;

    @Before
    public void setUp() {
        level = new Level(1, 1);
        InfoPanel infoPanel = new InfoPanel(level);
        SelectionTool selectionTool = new SelectionTool(infoPanel);
        DeletionTool deletionTool = new DeletionTool();
        PlacementRestriction placementRestriction = new PlacementRestriction();
        RoomTool roomTool = new RoomTool(level, deletionTool, placementRestriction);
        ExitBuilder exitBuilder = new ExitBuilder();
        ConnectionTool connectionTool = new ConnectionTool(deletionTool, exitBuilder);
        toolbar = new LevelToolbar(selectionTool, roomTool, connectionTool);
        levelPanel = new LevelPanel(level, toolbar);
    }

    @Test
    public void testConstructorGeneratesCellPanel() {
        Cell cell = level.getCellAt(new Point(0, 0));
        assertNotNull(cell.getCellPanel());
    }

}
