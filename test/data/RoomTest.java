package data;

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
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;

import java.awt.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RoomTest {

    Point testPoint;
    Cell testCell;
    CellPanel testCellPanel;
    Room testRoom;
    Level testLevel;
    Exit testExit;
    TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        testLevel = new Level();
        testingUtils.populateLevel(1, 1, testLevel);
        testPoint = new Point(1, 1);
        testCell = new Cell(testPoint, testLevel);
        testCellPanel = new CellPanel(testCell.X, testCell.Y);
        testRoom = new Room(testCell);
        testExit = new Exit(Direction.SOUTH, testRoom, ExitType.PATH);
        testRoom.addExit(testExit);
    }

    @Test
    public void testGetExit() {
        assertEquals(testExit, testRoom.getExit(Direction.SOUTH));
    }

    @Test
    public void testGetExitReturnsNullForNoExit() {
        assertNull(testRoom.getExit(Direction.NORTH));
    }

}
