package gui.infopanel;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers. Copyright (C) 2014 Fado@Epitaph.
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
import data.Room;
import data.Street;
import gui.levelpanel.CellPanel;
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;
import javax.swing.JComboBox;
import java.awt.Point;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class InfoPanelTest {

    Level level;
    InfoPanel infoPanel;
    CellPanel cellPanel;
    Room room;
    TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        level = new Level(1, 1);
        Cell cell = new Cell(new Point(0, 0), level);
        cellPanel = new CellPanel(cell);
        room = new Room(cellPanel);
        cell.setEntity(room);
        infoPanel = new InfoPanel(level);
    }

    @Test
    public void testLoadAndGetCurrentRoom() {
        infoPanel.load(cellPanel);
        assertEquals(room, infoPanel.getCurrentRoom());
    }

    @Test
    public void testUnload() {
        infoPanel.load(cellPanel);
        infoPanel.unload();
        assertNull(infoPanel.getCurrentRoom());
    }

    @Test
    public void testPopulateStreetNames() {
        level.addStreet(new Street("foo"));
        infoPanel.populateStreetNames();

        JComboBox streetNameField = testingUtils.getStreetNameField(infoPanel);
        if (streetNameField != null) {
            assertTrue(streetNameField.getItemCount() > 0);
        } else {
            fail();
        }
    }
}
