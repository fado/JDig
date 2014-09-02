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
import data.Level;
import data.Room;
import gui.levelpanel.CellPanel;
import gui.levelpanel.LevelPanel;
import gui.leveltoolbar.LevelToolbar;
import org.junit.Before;
import org.junit.Test;
import java.awt.Point;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class DeletionToolTest_5x5 {

    private Level testLevel;
    private Cell northCell, southCell, eastCell, westCell, northwestCell,
            northeastCell, southwestCell, southeastCell, middleCell;
    private CellPanel middleCellPanel;
    private DeletionTool testDeletionTool;
    private ExitBuilder exitBuilder;

    @Before
    public void setUp() {
        testLevel = new Level(5, 5);

        northCell = testLevel.getCellAt(new Point(2, 0));
        southCell = testLevel.getCellAt(new Point(2, 4));
        eastCell = testLevel.getCellAt(new Point(4, 2));
        westCell = testLevel.getCellAt(new Point(0, 2));
        northwestCell = testLevel.getCellAt(new Point(0, 0));
        northeastCell = testLevel.getCellAt(new Point(4, 0));
        southwestCell = testLevel.getCellAt(new Point(0, 4));
        southeastCell = testLevel.getCellAt(new Point(4, 4));
        middleCell = testLevel.getCellAt(new Point(2, 2));

        exitBuilder = new ExitBuilder();
        LevelPanel levelPanel = new LevelPanel(testLevel);
        testDeletionTool = new DeletionTool(levelPanel, testLevel);

        middleCellPanel = middleCell.getCellPanel();
    }

    /**
     * Test removing dead exits vertically.
     */
    @Test
    public void testRemoveDeadExitsNorth() {
        buildThreeVerticalRooms();
        testDeletionTool.deleteEntity(middleCellPanel);
        Room northRoom = (Room)northCell.getEntity();
        assertTrue(northRoom.getExits().isEmpty());
    }

    @Test
    public void testRemoveDeadExitsSouth() {
        buildThreeVerticalRooms();
        testDeletionTool.deleteEntity(middleCellPanel);
        Room southRoom = (Room)southCell.getEntity();
        assertTrue(southRoom.getExits().isEmpty());
    }

    private void buildThreeVerticalRooms() {
        northCell.setEntity(new Room(null));
        middleCell.setEntity(new Room(null));
        southCell.setEntity(new Room(null));
        Cell topConnector = testLevel.getCellAt(new Point(2, 1));
        topConnector.setEntity(new Connection(ConnectionType.VERTICAL));
        exitBuilder.build(topConnector);
        Cell bottomConnector = testLevel.getCellAt(new Point(2, 3));
        bottomConnector.setEntity(new Connection(ConnectionType.VERTICAL));
        exitBuilder.build(bottomConnector);
    }

    @Test
    public void testRemoveDeadExitsWest() {
        buildThreeHorizontalRooms();
        testDeletionTool.deleteEntity(middleCellPanel);
        Room westRoom = (Room)westCell.getEntity();
        assertTrue(westRoom.getExits().isEmpty());
    }

    @Test
    public void testRemoveDeadExitsEast() {
        buildThreeHorizontalRooms();
        testDeletionTool.deleteEntity(middleCellPanel);
        Room eastRoom = (Room)eastCell.getEntity();
        assertTrue(eastRoom.getExits().isEmpty());
    }

    private void buildThreeHorizontalRooms() {
        westCell.setEntity(new Room(null));
        middleCell.setEntity(new Room(null));
        eastCell.setEntity(new Room(null));
        Cell westConnector = testLevel.getCellAt(new Point(1, 2));
        westConnector.setEntity(new Connection(ConnectionType.HORIZONTAL));
        exitBuilder.build(westConnector);
        Cell eastConnector = testLevel.getCellAt(new Point(3, 2));
        eastConnector.setEntity(new Connection(ConnectionType.HORIZONTAL));
        exitBuilder.build(eastConnector);
    }

    @Test
    public void testRemoveDeadExitsNorthwest() {
        buildThreeBackDiagonalRooms();
        testDeletionTool.deleteEntity(middleCellPanel);
        Room northwestRoom = (Room)northwestCell.getEntity();
        assertTrue(northwestRoom.getExits().isEmpty());
    }

    @Test
    public void testRemoveDeadExitsSoutheast() {
        buildThreeBackDiagonalRooms();
        testDeletionTool.deleteEntity(middleCellPanel);
        Room southeastRoom = (Room)southeastCell.getEntity();
        assertTrue(southeastRoom.getExits().isEmpty());
    }

    private void buildThreeBackDiagonalRooms() {
        northwestCell.setEntity(new Room(null));
        middleCell.setEntity(new Room(null));
        southeastCell.setEntity(new Room(null));
        Cell northwestConnector = testLevel.getCellAt(new Point(1, 1));
        northwestConnector.setEntity(new Connection(ConnectionType.BACKWARD_DIAGONAL));
        exitBuilder.build(northwestConnector);
        Cell southeastConnector = testLevel.getCellAt(new Point(3, 3));
        southeastConnector.setEntity(new Connection(ConnectionType.BACKWARD_DIAGONAL));
        exitBuilder.build(southeastConnector);
    }

    @Test
    public void testRemoveDeadExitsNortheast() {
        buildThreeFwdDiagonalRooms();
        testDeletionTool.deleteEntity(middleCellPanel);
        Room northeastRoom = (Room)northeastCell.getEntity();
        assertTrue(northeastRoom.getExits().isEmpty());
    }

    @Test
    public void testRemoveDeadExitsSouthwest() {
        buildThreeFwdDiagonalRooms();
        testDeletionTool.deleteEntity(middleCellPanel);
        Room southwestRoom = (Room)southwestCell.getEntity();
        assertTrue(southwestRoom.getExits().isEmpty());
    }

    public void buildThreeFwdDiagonalRooms() {
        northeastCell.setEntity(new Room(null));
        middleCell.setEntity(new Room(null));
        southwestCell.setEntity(new Room(null));
        Cell northeastConnector = testLevel.getCellAt(new Point(3, 1));
        northeastConnector.setEntity(new Connection(ConnectionType.FORWARD_DIAGONAL));
        exitBuilder.build(northeastConnector);
        Cell southwestConnector = testLevel.getCellAt(new Point(1, 3));
        southwestConnector.setEntity(new Connection(ConnectionType.FORWARD_DIAGONAL));
        exitBuilder.build(southwestConnector);
    }

}
