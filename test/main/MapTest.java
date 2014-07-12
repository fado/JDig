package main;

import java.awt.Point;
import java.util.List;
import main.entities.Entity;
import main.entities.Exit;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

    Level testMap1, testMap2;
    Point testPoint, invalidPoint, defaultPoint, northPoint, southPoint,
            eastPoint, westPoint, northwestPoint, northeastPoint,
            southwestPoint, southeastPoint, centerPoint;

    @Before
    public void setUp() {
        testMap1 = new Level(1, 1);
        testMap2 = new Level(3, 3);

        testPoint = new Point(0, 0);
        invalidPoint = new Point(10, 10);
        defaultPoint = new Point(-1, -1);

        northwestPoint = new Point(0, 0);
        northPoint = new Point(1, 0);
        northeastPoint = new Point(2, 0);
        westPoint = new Point(0, 1);
        centerPoint = new Point(1, 1);
        eastPoint = new Point(2, 1);
        southwestPoint = new Point(0, 2);
        southPoint = new Point(1, 2);
        southeastPoint = new Point(2, 2);
    }

    @Test
    public void testGetAllCellsForOneCellMap() {
        List<Cell> allCells = testMap1.getAllCells();
        assertTrue(allCells.size() == 1);
    }
    
    @Test
    public void testGetAllCellsForMultiCellMap() {
        List<Cell> allCells = testMap2.getAllCells();
        assertTrue(allCells.size() == 9);
    }
    
    @Test
    public void testGetCellAtReturnsCorrectCell() {
        Cell cell = testMap1.getCellAt(testPoint);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(testPoint, actual);
    }
    
    @Test
    public void testGetCellAtReturnsDefaultCellForInvalidPoint() {
        Cell cell = testMap1.getCellAt(invalidPoint);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(defaultPoint, actual);
    }

    @Test
    public void testCellAdjacentNorthReturnsNorthForCenter() {
        Point expected = northPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(centerPoint), Direction.NORTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testCellAdjacentSouthReturnsSouthForCenter() {
        Point expected = southPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(centerPoint), Direction.SOUTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testCellAdjacentEastReturnsEastForCenter() {
        Point expected = eastPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(centerPoint), Direction.EAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testCellAdjacentWestReturnsWestForCenter() {
        Point expected = westPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(centerPoint), Direction.WEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testCellAdjacentNorthwestReturnsNorthwestForCenter() {
        Point expected = northwestPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(centerPoint), Direction.NORTHWEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testCellAdjacentNortheastReturnsNortheastForCenter() {
        Point expected = northeastPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(centerPoint), Direction.NORTHEAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testCellAdjacentSouthwestReturnsSouthwestForCenter() {
        Point expected = southwestPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(centerPoint), Direction.SOUTHWEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testCellAdjacentSoutheastReturnsSoutheastForCenter() {
        Point expected = southeastPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(centerPoint), Direction.SOUTHEAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testCellAdjacentReturnsDefaultCellWhenNoCellFound() {
        Point expected = defaultPoint;
        Cell cell = testMap2.getCellAdjacentTo(testMap2.getCellAt(northPoint), Direction.NORTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityFindsHorizontalExits() {
        testMap2.getCellAt(northwestPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(northeastPoint).setEntityType(Entity.ROOM);
        Exit expected = Exit.HORIZONTAL_EXIT;
        Exit actual = testMap2.getCellAt(northPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityFindsVerticalExits() {
        testMap2.getCellAt(northPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(southPoint).setEntityType(Entity.ROOM);
        Exit expected = Exit.VERTICAL_EXIT;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityFindsXExits() {
        testMap2.getCellAt(northwestPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(northeastPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(southwestPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(southeastPoint).setEntityType(Entity.ROOM);
        Exit expected = Exit.X_EXIT;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityFindsForwardDiagonalExits() {
        testMap2.getCellAt(northeastPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(southwestPoint).setEntityType(Entity.ROOM);
        Exit expected = Exit.FORWARD_DIAGONAL_EXIT;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialExitReturnsForwardDiagonalExitWithThreeRooms() {
        testMap2.getCellAt(northwestPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(northeastPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(southwestPoint).setEntityType(Entity.ROOM);
        Exit expected = Exit.FORWARD_DIAGONAL_EXIT;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityFindsBackwardDiagonalExits() {
        testMap2.getCellAt(northwestPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(southeastPoint).setEntityType(Entity.ROOM);
        Exit expected = Exit.BACKWARD_DIAGONAL_EXIT;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialExitReturnsBackwardDiagonalExitWithThreeRooms() {
        testMap2.getCellAt(northwestPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(northeastPoint).setEntityType(Entity.ROOM);
        testMap2.getCellAt(southeastPoint).setEntityType(Entity.ROOM);
        Exit expected = Exit.BACKWARD_DIAGONAL_EXIT;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityReturnsNoEntityForNoSurroundingRooms() {
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToWest() {
        testMap2.getCellAt(westPoint).setEntityType(Entity.ROOM);
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToEast() {
        testMap2.getCellAt(eastPoint).setEntityType(Entity.ROOM);
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentiaLEntityReturnsNoEntityForRoomToNorth() {
        testMap2.getCellAt(northPoint).setEntityType(Entity.ROOM);
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToSouth() {
        testMap2.getCellAt(southPoint).setEntityType(Entity.ROOM);
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToNorthwest() {
        testMap2.getCellAt(northwestPoint).setEntityType(Entity.ROOM);
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToNortheast() {
        testMap2.getCellAt(northeastPoint).setEntityType(Entity.ROOM);
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToSouthwest() {
        testMap2.getCellAt(southwestPoint).setEntityType(Entity.ROOM);
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToSoutheast() {
        testMap2.getCellAt(southeastPoint).setEntityType(Entity.ROOM);
        Exit expected = null;
        Exit actual = testMap2.getCellAt(centerPoint).getPotentialExitType();
        assertEquals(expected, actual);
    }

}
