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
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class LevelPanelTest {

    Level level;
    LevelPanel levelPanel;

    @Before
    public void setUp() {
        level = new Level(1, 1);
        levelPanel = new LevelPanel(level);
    }

    @Test
    public void testGetCellPanel() {
        Cell cell = level.getCellAt(new Point(0, 0));
        CellPanel cellPanel = levelPanel.getCellPanel(new Point(0, 0));
        assertEquals(cell.X, cellPanel.getPanelX());
    }

}
