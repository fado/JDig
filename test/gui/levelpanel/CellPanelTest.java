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
import data.ConnectionType;
import data.Level;
import data.Room;
import org.junit.Before;
import org.junit.Test;
import properties.Images;
import utils.TestingUtils;

import java.awt.Point;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CellPanelTest {

    Level level;
    Room room;
    Cell cell, cell2;
    CellPanel cellPanel, selectedCellPanel;
    Images images = new Images();
    TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        level = new Level();
        testingUtils.populateLevel(1, 1, level);
        cell = new Cell(new Point(0, 0), level);

        cell2 = new Cell(new Point(0, 0), level);
        room = new Room(null);
        cell2.setEntity(room);
        selectedCellPanel = new CellPanel(cell2);
        testingUtils.selectPanel(cell, selectedCellPanel);
        //selectedCellPanel.select();
    }

    @Test
    public void testConstructorDoesNotCallRestoreWithNoEntity() {
        cellPanel = new CellPanel(cell);
        assertNull(cellPanel.getEntityImagePath());
    }

    @Test
    public void testSelectWithEntityAddsImage() {
        Room room = new Room(null);
        cell.setEntity(room);
        cellPanel = new CellPanel(cell);
        testingUtils.selectPanel(cell, cellPanel);
        //cellPanel.select();
        String expected = room.getSelectedImage();
        String actual = cellPanel.getEntityImagePath();
        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithNoEntityDoesNotAddImage() {
        cellPanel = new CellPanel(cell);
        testingUtils.selectPanel(cell, cellPanel);
        //cellPanel.select();
        assertNull(cellPanel.getEntityImagePath());
    }

    @Test
    public void testHasEntityImageReturnsFalseForNoEntityImage() {
        cellPanel = new CellPanel(cell);
        assertFalse(cellPanel.hasEntityImage());
    }

    @Test
    public void testHasEntityImageReturnsTrueForEntityImage() {
        cellPanel = new CellPanel(cell);
        cellPanel.addImage(ConnectionType.VERTICAL.getPath());
        assertTrue(cellPanel.hasEntityImage());
    }
}
