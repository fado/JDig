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

import gui.CellPanel;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.assertEquals;

public class ExitTest {

    Level testLevel;
    Cell testCell;
    Point testPoint;
    CellPanel cellPanel;
    Room testRoom;
    Direction testDirection;
    ExitType testExitType;
    Exit testExit;

    @Before
    public void setUp() {
        testLevel = new Level(1, 1);
        testPoint = new Point(0, 0);
        testCell = new Cell(testPoint, testLevel);
        cellPanel = new CellPanel(testCell);
        testRoom = new Room(cellPanel);
        testDirection = Direction.NORTH;
        testExitType = ExitType.PATH;
        testExit = new Exit(testDirection, testRoom, testExitType);
    }

    @Test
    public void testGetDirection() {
        assertEquals(testDirection, testExit.getDirection());
    }

    @Test
    public void testGetDestination() {
        assertEquals(testRoom, testExit.getDestination());
    }

    @Test
    public void testGetExitType() {
        assertEquals(testExitType, testExit.getExitType());
    }

}
