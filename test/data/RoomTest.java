package data;

import gui.levelpanel.CellPanel;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.assertEquals;

public class RoomTest {

    Point testPoint;
    Cell testCell;
    CellPanel testCellPanel;
    Room testRoom;
    Level testLevel;
    Exit testExit;

    @Before
    public void setUp() {
        testLevel = new Level(1, 1);
        testPoint = new Point(1, 1);
        testCell = new Cell(testPoint, testLevel);
        testCellPanel = new CellPanel(testCell);
        testRoom = new Room(testCellPanel);
        testExit = new Exit(Direction.SOUTH, testRoom, ExitType.PATH);
        testRoom.addExit(testExit);
    }

    @Test
    public void testGetExit() {
        assertEquals(testExit, testRoom.getExit(Direction.SOUTH));
    }

}
