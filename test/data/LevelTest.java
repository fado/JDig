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
import static org.junit.Assert.assertTrue;

public class LevelTest {

    private Level testLevel, testLevel2, testLevel3, testLevel4, testLevel5;
    Point testPointDefault, testPointNorth, testPointSouth,
            testPointEast, testPointWest, testPointNorthwest, testPointNortheast,
            testPointSouthwest, testPointSoutheast, testPointCenter;
    Cell testCellNorth, testCellSouth, testCellEast, testCellWest,
            testCellNorthwest, testCellNortheast, testCellSouthwest,
            testCellSoutheast, testCellCenter;
    private Street testStreet;
    private String testStreetName;
    private Cell testCell;
    private CellPanel testCellPanel;
    private Point testPoint;
    private Room testRoom;

    @Before
    public void setUp() {
        testLevel = new Level(1, 1);
        testLevel2 = new Level(2, 2);
        testStreetName = "testStreetName";
        testStreet = new Street(testStreetName);
        testLevel3 = new Level(1, 1);
        testLevel3.addStreet(testStreet);
        testPoint = new Point(1, 1);
        testCell = new Cell(testPoint, testLevel);
        testCellPanel = new CellPanel(testCell);
        testRoom = new Room(testCellPanel);
        testLevel4 = new Level(1, 1);
        testLevel4.registerRoom(testRoom);

        testLevel5 = new Level(3, 3);

        testPointDefault = new Point(-1, -1);
        testPointNorthwest = new Point(0,0);
        testPointNorth = new Point(1,0);
        testPointNortheast = new Point(2,0);
        testPointWest = new Point(0,1);
        testPointCenter = new Point(1,1);
        testPointEast = new Point(2,1);
        testPointSouthwest = new Point(0,2);
        testPointSouth = new Point(1,2);
        testPointSoutheast = new Point(2,2);

        testCellNorthwest = new Cell(testPointNorthwest, testLevel5);
        testCellNorth = new Cell(testPointNorth, testLevel5);
        testCellNortheast = new Cell(testPointNortheast, testLevel5);
        testCellWest = new Cell(testPointWest, testLevel5);
        testCellCenter = new Cell(testPointCenter, testLevel5);
        testCellEast = new Cell(testPointEast, testLevel5);
        testCellSouthwest = new Cell(testPointSouthwest, testLevel5);
        testCellSouth = new Cell(testPointSouth, testLevel5);
        testCellSoutheast = new Cell(testPointSoutheast, testLevel5);
    }

    @Test
    public void testConstructorAddsOneCell() {
        assertTrue(testLevel.getAllCells().size() == 1);
    }

    @Test
    public void testConstructorAddsFourCells() {
        assertTrue(testLevel2.getAllCells().size() == 4);
    }

    @Test
    public void testGetNorthAdjacent() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellCenter, Direction.NORTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointNorth, actual);
    }

    @Test
    public void testGetSouthAdjacent() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellCenter, Direction.SOUTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointSouth, actual);
    }

    @Test
    public void testGetEastAdjacent() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellCenter, Direction.EAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointEast, actual);
    }

    @Test
    public void testGetWestAdjacent() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellCenter, Direction.WEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointWest, actual);
    }

    @Test
    public void testGetNorthwestAdjacent() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellCenter, Direction.NORTHWEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointNorthwest, actual);
    }

    @Test
    public void testGetNortheastAdjacent() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellCenter, Direction.NORTHEAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointNortheast, actual);
    }

    @Test
    public void testGetSouthwestAdjacent() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellCenter, Direction.SOUTHWEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointSouthwest, actual);
    }

    @Test
    public void testGetSoutheastAdjacent() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellCenter, Direction.SOUTHEAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointSoutheast, actual);
    }

    @Test
    public void testDefaultReturned() {
        Cell cell = testLevel5.getCellAdjacentTo(testCellNorth, Direction.NORTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPointDefault, actual);
    }

}
