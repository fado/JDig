package tools;

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
import data.Connection;
import data.ConnectionType;
import data.Direction;
import data.Exit;
import data.ExitType;
import data.Level;
import data.Room;
import gui.levelpanel.CellPanel;
import gui.levelpanel.LevelPanel;
import main.BindingService;
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;

import java.awt.Point;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ExitCommandBuilderTest {

    Level testLevel;
    Point northPoint, southPoint, eastPoint, westPoint, northwestPoint,
        northeastPoint, southwestPoint, southeastPoint, middlePoint;
    Cell northCell, southCell, eastCell, westCell, northwestCell,
        northeastCell, southwestCell, southeastCell, middleCell;
    CellPanel northCellPanel, southCellPanel, eastCellPanel, westCellPanel,
            northwestCellPanel, northeastCellPanel, southwestCellPanel,
            southeastCellPanel, middleCellPanel;
    TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        testLevel = new Level();
        testingUtils.populateLevel(3, 3, testLevel);
        northwestPoint = new Point(0, 0);
        northeastPoint = new Point(2, 0);
        southwestPoint = new Point(0, 2);
        southeastPoint = new Point(2, 2);
        northPoint = new Point(1, 0);
        southPoint = new Point(1, 2);
        eastPoint = new Point(2, 1);
        westPoint = new Point(0, 1);
        middlePoint = new Point(1, 1);
        northCell = testLevel.getCellAt(northPoint);
        southCell = testLevel.getCellAt(southPoint);
        eastCell = testLevel.getCellAt(eastPoint);
        westCell = testLevel.getCellAt(westPoint);
        northwestCell = testLevel.getCellAt(northwestPoint);
        northeastCell = testLevel.getCellAt(northeastPoint);
        southwestCell = testLevel.getCellAt(southwestPoint);
        southeastCell = testLevel.getCellAt(southeastPoint);
        middleCell = testLevel.getCellAt(middlePoint);

        TestingUtils testingUtils = new TestingUtils();
        BindingService bindingService = new BindingService(testLevel);
        bindingService = testingUtils.setupBindingService(bindingService, testLevel);
        LevelPanel levelPanel = testingUtils.buildLevelPanel(testLevel, bindingService);
        northCellPanel = bindingService.getBoundCellPanel(northCell);
        southCellPanel = bindingService.getBoundCellPanel(southCell);
        eastCellPanel = bindingService.getBoundCellPanel(eastCell);
        westCellPanel = bindingService.getBoundCellPanel(westCell);
        northwestCellPanel = bindingService.getBoundCellPanel(northwestCell);
        northeastCellPanel = bindingService.getBoundCellPanel(northeastCell);
        southwestCellPanel = bindingService.getBoundCellPanel(southwestCell);
        southeastCellPanel = bindingService.getBoundCellPanel(southeastCell);
        middleCellPanel = bindingService.getBoundCellPanel(middleCell);
    }

    /**
     * Test south exit, north room.
     */

    @Test
    public void testSouthExitDirection() {
        setupVerticalRooms();
        Direction expected = Direction.SOUTH;
        assertEquals(expected, getDirection(northCell));
    }

    @Test
    public void testSouthExitDestination() {
        setupVerticalRooms();
        Room expected = (Room)southCell.getEntity();
        assertEquals(expected, getDestination(northCell));
    }

    @Test
    public void testSouthExitType() {
        setupVerticalRooms();
        ExitType expected = ExitType.PATH;
        assertEquals(expected, getExitType(northCell));
    }

    /**
     * Test north exit, south room.
     */

    @Test
    public void testNorthExitDirection() {
        setupVerticalRooms();
        Direction expected = Direction.NORTH;
        assertEquals(expected, getDirection(southCell));
    }

    @Test
    public void testNorthExitDestination() {
        setupVerticalRooms();
        Room expected = (Room)northCell.getEntity();
        assertEquals(expected, getDestination(southCell));
    }

    @Test
    public void testNorthExitType() {
        setupVerticalRooms();
        ExitType expected = ExitType.PATH;
        assertEquals(expected, getExitType(southCell));
    }

    /**
     * Utility method to set up three rooms in vertical alignment.
     */
    private void setupVerticalRooms() {
        northCell.setEntity(new Room(northCell));
        middleCell.setEntity(new Connection(ConnectionType.VERTICAL));
        southCell.setEntity(new Room(southCell));
        new ExitBuilder().build(middleCell);
    }

    /**
     * Test east exit, west room.
     */
    @Test
    public void testEastExitDirection() {
        setupHorizontalRooms();
        Direction expected = Direction.EAST;
        assertEquals(expected, getDirection(westCell));
    }

    @Test
    public void testEastExitDestination() {
        setupHorizontalRooms();
        Room expected = (Room)eastCell.getEntity();
        assertEquals(expected, getDestination(westCell));
    }

    @Test
    public void testEastExitType() {
        setupHorizontalRooms();
        ExitType expected = ExitType.PATH;
        assertEquals(expected, getExitType(westCell));
    }

    /**
     * Test west exit, east room.
     */
    @Test
    public void testWestExitDirection() {
        setupHorizontalRooms();
        Direction expected = Direction.WEST;
        assertEquals(expected, getDirection(eastCell));
    }

    @Test
    public void testWestExitDestination() {
        setupHorizontalRooms();
        Room expected = (Room)westCell.getEntity();
        assertEquals(expected, getDestination(eastCell));
    }

    @Test
    public void testWestExitType() {
        setupHorizontalRooms();
        ExitType expected = ExitType.PATH;
        assertEquals(expected, getExitType(eastCell));
    }

    /**
    * Utility method to set up three rooms in horizontal alignment.
    */
    private void setupHorizontalRooms() {
        westCell.setEntity(new Room(westCell));
        middleCell.setEntity(new Connection(ConnectionType.HORIZONTAL));
        eastCell.setEntity(new Room(eastCell));
        new ExitBuilder().build(middleCell);
    }

    /**
     * Test southeast exit, northwest room.
     */
    @Test
    public void testSoutheastExitDirection() {
        setupBackwardDiagonalRooms();
        Direction expected = Direction.SOUTHEAST;
        assertEquals(expected, getDirection(northwestCell));
    }

    @Test
    public void testSoutheastExitDestination() {
        setupBackwardDiagonalRooms();
        Room expected = (Room)southeastCell.getEntity();
        assertEquals(expected, getDestination(northwestCell));
    }

    @Test
    public void testSoutheastExitType() {
        setupBackwardDiagonalRooms();
        ExitType expected = ExitType.PATH;
        assertEquals(expected, getExitType(northwestCell));
    }

    /**
     * Test northwest exit, southeast room.
     */
    @Test
    public void testNorthwestExitDirection() {
        setupBackwardDiagonalRooms();
        Direction expected = Direction.NORTHWEST;
        assertEquals(expected, getDirection(southeastCell));
    }

    @Test
    public void testNorthwestExitDestination() {
        setupBackwardDiagonalRooms();
        Room expected = (Room)northwestCell.getEntity();
        assertEquals(expected, getDestination(southeastCell));
    }

    @Test
    public void testNorthwestExitType() {
        setupBackwardDiagonalRooms();
        ExitType expected = ExitType.PATH;
        assertEquals(expected, getExitType(southeastCell));
    }

    /**
     * Utility method to set up three rooms in diagonal alignment.
     */
    private void setupBackwardDiagonalRooms() {
        northwestCell.setEntity(new Room(northwestCell));
        middleCell.setEntity(new Connection(ConnectionType.BACKWARD_DIAGONAL));
        southeastCell.setEntity(new Room(southeastCell));
        new ExitBuilder().build(middleCell);
    }

    /**
     * Test southwest exit, northeast room.
     */
    @Test
    public void testSouthwestExitDirection() {
        setupForwardDiagonalRooms();
        Direction expected = Direction.SOUTHWEST;
        assertEquals(expected, getDirection(northeastCell));
    }

    @Test
    public void testSouthwestExitDestination() {
        setupForwardDiagonalRooms();
        Room expected = (Room)southwestCell.getEntity();
        assertEquals(expected, getDestination(northeastCell));
    }

    @Test
    public void testSouthwestExitType() {
        setupForwardDiagonalRooms();
        ExitType expected = ExitType.PATH;
        assertEquals(expected, getExitType(northeastCell));
    }

    /**
     * Test northeast exist, southwest room.
     */
    @Test
    public void testNortheastExitDirection() {
        setupForwardDiagonalRooms();
        Direction expected = Direction.NORTHEAST;
        assertEquals(expected, getDirection(southwestCell));
    }

    @Test
    public void testNortheastExitDestination() {
        setupForwardDiagonalRooms();
        Room expected = (Room)northeastCell.getEntity();
        assertEquals(expected, getDestination(southwestCell));
    }

    @Test
    public void testNortheastExitType() {
        setupForwardDiagonalRooms();
        ExitType expected = ExitType.PATH;
        assertEquals(expected, getExitType(southwestCell));
    }

    /**
     * Utility method to set up three rooms in diagonal alignment.
     *
     */
    private void setupForwardDiagonalRooms() {
        northeastCell.setEntity(new Room(northeastCell));
        middleCell.setEntity(new Connection(ConnectionType.FORWARD_DIAGONAL));
        southwestCell.setEntity(new Room(southwestCell));
        new ExitBuilder().build(middleCell);
    }

    /**
     * Utility method. Gets the Exit Direction.
     */
    private Direction getDirection(Cell cell) {
        return getExits(cell).get(0).getDirection();
    }

    /**
     * Utility method. Gets the Exit Destination.
     */
    private Room getDestination(Cell cell) {
        return getExits(cell).get(0).getDestination();
    }

    /**
     * Utility method. Gets the ExitType.
     */
    private ExitType getExitType(Cell cell) {
        return getExits(cell).get(0).getExitType();
    }

    /**
     * Utility method. Gets Exits from the passed-in Cell.
     */
    private List<Exit> getExits(Cell cell) {
        Room room = (Room)cell.getEntity();
        return room.getExits();
    }

}
