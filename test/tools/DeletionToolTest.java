package tools;

import data.Cell;
import data.Connection;
import data.ConnectionType;
import data.Level;
import data.Room;
import gui.CellPanel;
import java.awt.Point;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class DeletionToolTest {

    private Level testLevel, testLevelSpy, testLevel2;
    private Cell testCell, northCell, southCell, eastCell, westCell, northwestCell,
        northeastCell, southwestCell, southeastCell, middleCell;
    private CellPanel testCellPanel;
    private DeletionTool testDeletionTool;
    private CellTool cellTool, cellToolSpy;
    private Room testRoom;
    ExitBuilder exitBuilder;

    @Before
    public void setUp() {
        testLevel = new Level(1, 1);
        testLevel2 = new Level(3, 3);
        testLevelSpy = Mockito.spy(testLevel);
        testCell = new Cell(new Point(0, 0), testLevel);
        testCellPanel = new CellPanel(testCell);
        testRoom = new Room(testCellPanel);
        cellTool = new CellTool();
        cellToolSpy = Mockito.spy(cellTool);
        testDeletionTool = new DeletionTool(cellTool);
        northCell = testLevel2.getCellAt(new Point(1, 0));
        southCell = testLevel2.getCellAt(new Point(1, 2));
        eastCell = testLevel2.getCellAt(new Point(2, 1));
        westCell = testLevel2.getCellAt(new Point(0, 1));
        northwestCell = testLevel2.getCellAt(new Point(0, 0));
        northeastCell = testLevel2.getCellAt(new Point(2, 0));
        southwestCell = testLevel2.getCellAt(new Point(0, 2));
        southeastCell = testLevel2.getCellAt(new Point(2, 2));
        middleCell = testLevel2.getCellAt(new Point(1, 1));
        exitBuilder = new ExitBuilder();
    }

    /**
     * Test that the DeletionTool will unregister a Room if it exists
     * in the CellPanel.
     */
    @Test
    public void testDeleteEntityUnregistersRoom() {
        testCell.setEntity(testRoom);
        testLevel.registerRoom(testRoom);
        testDeletionTool.deleteEntity(testCellPanel);
        assertTrue(testLevel.getRooms().isEmpty());
    }

    /**
     * Test that the DeletionTool won't attempt to unregister a Room
     * that doesn't exist.
     */
    @Test
    public void testDeleteEntityDoesNotUnregisterRoom() {
        testDeletionTool.deleteEntity(testCellPanel);
        verify(testLevelSpy, never()).unregisterRoom(any(Room.class));
    }

    /**
     * Test that the DeletionTool will deselect a CellPanel that's already
     * selected.
     */
    @Test
    public void testDeleteEntityDeselectsCellPanel() {
        cellTool.setSelected(testCellPanel);
        testDeletionTool.deleteEntity(testCellPanel);
        assertFalse(testCellPanel.isSelected());
    }

    /**
     * Test that the DeletionTool won't attempt to deselect a CellPanel that
     * isn't selected.
     */
    @Test
    public void testDeleteEntityCellPanelNotSelected() {
        testDeletionTool.deleteEntity(testCellPanel);
        verify(cellToolSpy, never()).setDeselected(testCellPanel);
    }

    /**
     * Test west room gets deleted with horizontal Connection.
     */
    @Test
    public void testDeleteHorizontalExitWest() {
        buildHorizontalRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room westRoom = (Room)westCell.getEntity();
        assertTrue(westRoom.getExits().isEmpty());
    }

    /**
     * Test east room gets deleted with horizontal Connection.
     */
    @Test
    public void testDeleteHorizontalExitEast() {
        buildHorizontalRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room eastRoom = (Room)eastCell.getEntity();
        assertTrue(eastRoom.getExits().isEmpty());
    }

    /**
     * Test north room gets deleted with vertical Connection.
     */
    @Test
    public void testDeleteVerticalExitNorth() {
        buildVerticalRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room northRoom = (Room)northCell.getEntity();
        assertTrue(northRoom.getExits().isEmpty());
    }

    /**
     * Test south room gets deleted with vertical Connection.
     */
    @Test
    public void testDeleteVerticalExitSouth() {
        buildVerticalRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room southRoom = (Room)southCell.getEntity();
        assertTrue(southRoom.getExits().isEmpty());
    }

    /**
     * Test northeast room gets deleted with diagonal rooms.
     */
    @Test
    public void testDeleteFwdDiagonalNortheast() {
        buildForwardDiagonalRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room northeastRoom = (Room)northeastCell.getEntity();
        assertTrue(northeastRoom.getExits().isEmpty());
    }

    /**
     * Test southwest room gets deleted with diagonal rooms.
     */
    @Test
    public void testDeleteFwdDiagonalSouthwest() {
        buildForwardDiagonalRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room southwestRoom = (Room)southwestCell.getEntity();
        assertTrue(southwestRoom.getExits().isEmpty());
    }

    /**
     * Test northwest room gets deleted with diagonal rooms.
     */
    @Test
    public void testDeleteBwdDiagonalNorthwest() {
        buildBackwardDiagonalRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room northwestRoom = (Room)northwestCell.getEntity();
        assertTrue(northwestRoom.getExits().isEmpty());
    }

    /**
     * Test southeast room gets deleted with diagonal rooms.
     */
    @Test
    public void testDeleteBwdDiagonalSoutheast() {
        buildBackwardDiagonalRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room southeastRoom = (Room)southeastCell.getEntity();
        assertTrue(southeastRoom.getExits().isEmpty());
    }

    /**
     * Test northwest room gets deleted with rooms in four corners.
     */
    @Test
    public void testDeleteXNorthwest() {
        buildFourCornerRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room northwestRoom = (Room)northwestCell.getEntity();
        assertTrue(northwestRoom.getExits().isEmpty());
    }

    /**
     * Test northeast room gets deleted with rooms in four corners.
     */
    @Test
    public void testDeleteXNortheast() {
        buildFourCornerRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room northeastRoom = (Room)northwestCell.getEntity();
        assertTrue(northeastRoom.getExits().isEmpty());
    }

    /**
     * Test southwest room gets deleted with rooms in four corners.
     */
    @Test
    public void testDeleteXSouthwest() {
        buildFourCornerRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room southwestRoom = (Room)southwestCell.getEntity();
        assertTrue(southwestRoom.getExits().isEmpty());
    }

    /**
     * Test southeast room gets deleted with rooms in four corners.
     */
    @Test
    public void testDeleteXSoutheast() {
        buildFourCornerRooms();
        testDeletionTool.deleteEntity(middleCell.getCellPanel());
        Room southeastRoom = (Room)southeastCell.getEntity();
        assertTrue(southeastRoom.getExits().isEmpty());
    }

    /**
     * Utility method to build horizontal rooms.
     */
    private void buildHorizontalRooms() {
        westCell.setEntity(new Room(null));
        eastCell.setEntity(new Room(null));
        middleCell.setCellPanel(new CellPanel(middleCell));
        middleCell.setEntity(new Connection(ConnectionType.HORIZONTAL));
        exitBuilder.build(middleCell);
    }

    /**
     * Utility method to build vertical rooms.
     */
    private void buildVerticalRooms() {
        northCell.setEntity(new Room(null));
        southCell.setEntity(new Room(null));
        middleCell.setCellPanel(new CellPanel(middleCell));
        middleCell.setEntity(new Connection(ConnectionType.VERTICAL));
        exitBuilder.build(middleCell);
    }

    /**
     * Utility method to build forward diagonal rooms.
     */
    private void buildForwardDiagonalRooms() {
        northeastCell.setEntity(new Room(null));
        southwestCell.setEntity(new Room(null));
        middleCell.setCellPanel(new CellPanel(middleCell));
        middleCell.setEntity(new Connection(ConnectionType.FORWARD_DIAGONAL));
        exitBuilder.build(middleCell);
    }

    /**
     * Utility method to build backward diagonal rooms.
     */
    private void buildBackwardDiagonalRooms() {
        northwestCell.setEntity(new Room(null));
        southeastCell.setEntity(new Room(null));
        middleCell.setCellPanel(new CellPanel(middleCell));
        middleCell.setEntity(new Connection(ConnectionType.BACKWARD_DIAGONAL));
        exitBuilder.build(middleCell);
    }

    /**
     * Utility method to build rooms in four corners.
     */
    private void buildFourCornerRooms() {
        northeastCell.setEntity(new Room(null));
        southwestCell.setEntity(new Room(null));
        northwestCell.setEntity(new Room(null));
        southeastCell.setEntity(new Room(null));
        middleCell.setCellPanel(new CellPanel(middleCell));
        middleCell.setEntity(new Connection(ConnectionType.X));
        exitBuilder.build(middleCell);
    }

}