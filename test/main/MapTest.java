package main;

import java.awt.Point;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MapTest {
    
    Map testMap1, testMap2;
    Point testPoint, testPointDefault, testPointNorth, testPointSouth, 
            testPointEast, testPointWest, testPointNorthwest, testPointNortheast, 
            testPointSouthwest, testPointSoutheast, testPointCenter;
    Cell testCell, testCellNorth, testCellSouth, testCellEast, testCellWest,
            testCellNorthwest, testCellNortheast, testCellSouthwest,
            testCellSoutheast, testCellCenter;
    
    @Before
    public void setUp() {
        testMap1 = new Map();
        testMap2 = new Map();
        
        testPoint = new Point(0,0);
        testPointDefault = new Point(-1, -1);
        
        testCell = new Cell(testPoint, testMap1);
        
        testPointNorthwest = new Point(0,0);
        testPointNorth = new Point(1,0);
        testPointNortheast = new Point(2,0);
        testPointWest = new Point(0,1);
        testPointCenter = new Point(1,1);
        testPointEast = new Point(2,1);
        testPointSouthwest = new Point(0,2);
        testPointSouth = new Point(1,2);
        testPointSoutheast = new Point(2,2);
        
        testCellNorthwest = new Cell(testPointNorthwest, testMap2);
        testCellNorth = new Cell(testPointNorth, testMap2);
        testCellNortheast = new Cell(testPointNortheast, testMap2);
        testCellWest = new Cell(testPointWest, testMap2);
        testCellCenter = new Cell(testPointCenter, testMap2);
        testCellEast = new Cell(testPointEast, testMap2);
        testCellSouthwest = new Cell(testPointSouthwest, testMap2);
        testCellSouth = new Cell(testPointSouth, testMap2);
        testCellSoutheast = new Cell(testPointSoutheast, testMap2);
        
        testMap2.addCell(testCellNorthwest);
        testMap2.addCell(testCellNorth);
        testMap2.addCell(testCellNortheast);
        testMap2.addCell(testCellWest);
        testMap2.addCell(testCellCenter);
        testMap2.addCell(testCellEast);
        testMap2.addCell(testCellSouthwest);
        testMap2.addCell(testCellSouth);
        testMap2.addCell(testCellSoutheast);
    }
    
    @Test
    public void testAddCellAndGetAllCells() {
        testMap1.addCell(testCell);
        List<Cell> allCells = testMap1.getAllCells();
        assertTrue(allCells.size() == 1);
        assertTrue(allCells.contains(testCell));
    }
    
    @Test
    public void testCellAdjacentNorthReturnsNorthForCenter() {
        Point expected = new Point(testCellNorth.X, testCellNorth.Y);
        Cell cell = testMap2.getCellAdjacentTo(testCellCenter, Direction.NORTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCellAdjacentSouthReturnsSouthForCenter() {
        Point expected = new Point(testCellSouth.X, testCellSouth.Y);
        Cell cell = testMap2.getCellAdjacentTo(testCellCenter, Direction.SOUTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCellAdjacentEastReturnsEastForCenter() {
        Point expected = new Point(testCellEast.X, testCellEast.Y);
        Cell cell = testMap2.getCellAdjacentTo(testCellCenter, Direction.EAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCellAdjacentWestReturnsWestForCenter() {
        Point expected = new Point(testCellWest.X, testCellWest.Y);
        Cell cell = testMap2.getCellAdjacentTo(testCellCenter, Direction.WEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCellAdjacentNorthwestReturnsNorthwestForCenter() {
        Point expected = new Point(testCellNorthwest.X, testCellNorthwest.Y);
        Cell cell = testMap2.getCellAdjacentTo(testCellCenter, Direction.NORTHWEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCellAdjacentNortheastReturnsNortheastForCenter() {
        Point expected = new Point(testCellNortheast.X, testCellNortheast.Y);
        Cell cell = testMap2.getCellAdjacentTo(testCellCenter, Direction.NORTHEAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCellAdjacentSouthwestReturnsSouthwestForCenter() {
        Point expected = new Point(testCellSouthwest.X, testCellSouthwest.Y);
        Cell cell = testMap2.getCellAdjacentTo(testCellCenter, Direction.SOUTHWEST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCellAdjacentSoutheastReturnsSoutheastForCenter() {
        Point expected = new Point(testCellSoutheast.X, testCellSoutheast.Y);
        Cell cell = testMap2.getCellAdjacentTo(testCellCenter, Direction.SOUTHEAST);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCellAdjacentReturnsDefaultCellWhenNoCellFound() {
        Point expected = testPointDefault;
        Cell cell = testMap2.getCellAdjacentTo(testCellNorth, Direction.NORTH);
        Point actual = new Point(cell.X, cell.Y);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityFindsHorizontalExits() {
        testCellNorthwest.setEntity(Entity.ROOM);
        testCellNortheast.setEntity(Entity.ROOM);
        Entity expected = Entity.HORIZONTAL_EXIT;
        Entity actual = testMap2.getPotentialEntity(testCellNorth);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityFindsVerticalExits() {
        testCellNorth.setEntity(Entity.ROOM);
        testCellSouth.setEntity(Entity.ROOM);
        Entity expected = Entity.VERTICAL_EXIT;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityFindsXExits() {
        testCellNorthwest.setEntity(Entity.ROOM);
        testCellNortheast.setEntity(Entity.ROOM);
        testCellSouthwest.setEntity(Entity.ROOM);
        testCellSoutheast.setEntity(Entity.ROOM);
        Entity expected = Entity.X_EXIT;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityFindsForwardDiagonalExits() {
        testCellNortheast.setEntity(Entity.ROOM);
        testCellSouthwest.setEntity(Entity.ROOM);
        Entity expected = Entity.FORWARD_DIAGONAL_EXIT;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialExitReturnsForwardDiagonalExitWithThreeRooms() {
        testCellNorthwest.setEntity(Entity.ROOM);
        testCellNortheast.setEntity(Entity.ROOM);
        testCellSouthwest.setEntity(Entity.ROOM);
        Entity expected = Entity.FORWARD_DIAGONAL_EXIT;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityFindsBackwardDiagonalExits() {
        testCellNorthwest.setEntity(Entity.ROOM);
        testCellSoutheast.setEntity(Entity.ROOM);
        Entity expected = Entity.BACKWARD_DIAGONAL_EXIT;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialExitReturnsBackwardDiagonalExitWithThreeRooms() {
        testCellNorthwest.setEntity(Entity.ROOM);
        testCellNortheast.setEntity(Entity.ROOM);
        //testCellSouthwest.setEntity(Entity.ROOM);
        testCellSoutheast.setEntity(Entity.ROOM);        
        Entity expected = Entity.FORWARD_DIAGONAL_EXIT;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityReturnsNoEntityForNoSurroundingRooms() {
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToWest() {
        testCellWest.setEntity(Entity.ROOM);
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToEast() {
        testCellEast.setEntity(Entity.ROOM);
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentiaLEntityReturnsNoEntityForRoomToNorth() {
        testCellNorth.setEntity(Entity.ROOM);
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToSouth() {
        testCellSouth.setEntity(Entity.ROOM);
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToNorthwest() {
        testCellNorthwest.setEntity(Entity.ROOM);
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToNortheast() {
        testCellNortheast.setEntity(Entity.ROOM);
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToSouthwest() {
        testCellSouthwest.setEntity(Entity.ROOM);
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetPotentialEntityReturnsNoEntityForRoomToSoutheast() {
        testCellSoutheast.setEntity(Entity.ROOM);
        Entity expected = Entity.NO_ENTITY;
        Entity actual = testMap2.getPotentialEntity(testCellCenter);
        assertEquals(expected, actual);
    }
    
}
