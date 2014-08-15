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

import java.awt.Color;
import java.awt.Point;
import java.util.Map;

import static org.junit.Assert.*;

import gui.CellPanel;
import org.junit.Before;
import org.junit.Test;

public class CellTest {

    private Level testLevel1, testLevel2;
    private ConnectionType testConnectionType;
    private Entity testConnection;
    private Entity testRoom;
    private Point testPoint, testPointInvalidX, testPointInvalidY;
    private Cell testCell1, testCell2, testCell3;
    private CellPanel testCellPanel;
    private Color testColor;

    @Before
    public void setUp() {
        testLevel1 = new Level(1, 1);
        testConnectionType = ConnectionType.NONE;
        testConnection = new Connection(testConnectionType);
        testPoint = new Point(1, 1);
        testPointInvalidX = new Point(-1, 1);
        testPointInvalidY = new Point(1, -1);
        testCell1 = new Cell(testPoint, testLevel1);
        testCell2 = new Cell(testPointInvalidX, testLevel1);
        testCell3 = new Cell(testPointInvalidY, testLevel1);
        testCellPanel = new CellPanel(testCell1);
        testRoom = new Room(testCellPanel);
        testColor = new Color(0, 0, 0);

        testLevel2 = new Level(3, 3);
    }

    @Test
    public void testGetAndSetConnectionEntity() {
        testCell1.setEntity(testConnection);
        assertEquals(testConnection, testCell1.getEntity());
    }

    @Test
    public void testGetAndSetRoomEntity() {
        testCell1.setEntity(testRoom);
        assertEquals(testRoom, testCell1.getEntity());
    }

    @Test
    public void testGetAndSetCellPanel() {
        testCell1.setCellPanel(testCellPanel);
        assertEquals(testCellPanel, testCell1.getCellPanel());
    }

    @Test
    public void testGetAndSetColor() {
        testCell1.setColor(testColor);
        assertEquals(testColor, testCell1.getColor());
    }

    @Test
    public void testIsConnectibleFalse() {
        testCell1.setEntity(testConnection);
        assertFalse(testCell1.isConnectible());
    }

    @Test
    public void testIsConnectibleTrue() {
        testCell1.setEntity(testRoom);
        assertTrue(testCell1.isConnectible());
    }

    @Test
    public void testIsExitFalse() {
        testCell1.setEntity(testRoom);
        assertFalse(testCell1.isExit());
    }

    @Test
    public void testIsInBoundsInvalidX() {
        assertFalse(testCell2.isInBounds());
    }

    @Test
    public void testIsInBoundsInvalidY() {
        assertFalse(testCell3.isInBounds());
    }

    @Test
    public void testIsInBoundsValidPoint() {
        assertTrue(testCell1.isInBounds());
    }

    @Test
    public void testGetHorizontalConnectionType() {
        testLevel2.getCellAt(new Point(0, 1)).setEntity(testRoom);
        testLevel2.getCellAt(new Point(2, 1)).setEntity(testRoom);
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        ConnectionType expected = ConnectionType.HORIZONTAL;
        ConnectionType actual = middleCell.getPotentialConnectionType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetVerticalConnectionType() {
        testLevel2.getCellAt(new Point(1, 0)).setEntity(testRoom);
        testLevel2.getCellAt(new Point(1, 2)).setEntity(testRoom);
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        ConnectionType expected = ConnectionType.VERTICAL;
        ConnectionType actual = middleCell.getPotentialConnectionType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetXConnectionType() {
        testLevel2.getCellAt(new Point(0, 0)).setEntity(testRoom);
        testLevel2.getCellAt(new Point(2, 0)).setEntity(testRoom);
        testLevel2.getCellAt(new Point(0, 2)).setEntity(testRoom);
        testLevel2.getCellAt(new Point(2, 2)).setEntity(testRoom);
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        ConnectionType expected = ConnectionType.X;
        ConnectionType actual = middleCell.getPotentialConnectionType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBackwardDiagonalConnectionType() {
        testLevel2.getCellAt(new Point(0, 0)).setEntity(testRoom);
        testLevel2.getCellAt(new Point(2, 2)).setEntity(testRoom);
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        ConnectionType expected = ConnectionType.BACKWARD_DIAGONAL;
        ConnectionType actual = middleCell.getPotentialConnectionType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetForwardDiagonalConnectionType() {
        testLevel2.getCellAt(new Point(2, 0)).setEntity(testRoom);
        testLevel2.getCellAt(new Point(0, 2)).setEntity(testRoom);
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        ConnectionType expected = ConnectionType.FORWARD_DIAGONAL;
        ConnectionType actual = middleCell.getPotentialConnectionType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNoConnectionType() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        ConnectionType expected = ConnectionType.NONE;
        ConnectionType actual = middleCell.getPotentialConnectionType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNorthwestAdjacent() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        Map<String, Cell> cells = middleCell.getAdjacentCells();
        Cell northwestCell = cells.get("northwestCell");
        Point expected = new Point(0, 0);
        Point actual = new Point(northwestCell.X, northwestCell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNortheastAdjacent() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        Map<String, Cell> cells = middleCell.getAdjacentCells();
        Cell northwestCell = cells.get("northeastCell");
        Point expected = new Point(2, 0);
        Point actual = new Point(northwestCell.X, northwestCell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSouthwestAdjacent() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        Map<String, Cell> cells = middleCell.getAdjacentCells();
        Cell northwestCell = cells.get("southwestCell");
        Point expected = new Point(0, 2);
        Point actual = new Point(northwestCell.X, northwestCell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSoutheastAdjacent() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        Map<String, Cell> cells = middleCell.getAdjacentCells();
        Cell northwestCell = cells.get("southeastCell");
        Point expected = new Point(2, 2);
        Point actual = new Point(northwestCell.X, northwestCell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetNorthAdjacent() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        Map<String, Cell> cells = middleCell.getAdjacentCells();
        Cell northwestCell = cells.get("northCell");
        Point expected = new Point(1, 0);
        Point actual = new Point(northwestCell.X, northwestCell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSouthAdjacent() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        Map<String, Cell> cells = middleCell.getAdjacentCells();
        Cell northwestCell = cells.get("southCell");
        Point expected = new Point(1, 2);
        Point actual = new Point(northwestCell.X, northwestCell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetWestAdjacent() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        Map<String, Cell> cells = middleCell.getAdjacentCells();
        Cell northwestCell = cells.get("westCell");
        Point expected = new Point(0, 1);
        Point actual = new Point(northwestCell.X, northwestCell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEastAdjacent() {
        Cell middleCell = testLevel2.getCellAt(new Point(1, 1));
        Map<String, Cell> cells = middleCell.getAdjacentCells();
        Cell northwestCell = cells.get("eastCell");
        Point expected = new Point(2, 1);
        Point actual = new Point(northwestCell.X, northwestCell.Y);
        assertEquals(expected, actual);
    }

}
