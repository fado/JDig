
package main;

import java.awt.Point;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class CellTest {

    private int validXCoord, validYCoord, invalidXCoord, invalidYCoord;
    private Cell testCellInBounds, testCellOutOfBounds, testCellXOutOfBounds,
            testCellYOutOfBounds;
    private Point validTestPoint, invalidTestPoint, invalidXPoint, invalidYPoint;
    private Map testMap;
    private Entity defaultEntity;
    
    @Before
    public void setUp() {
        validXCoord = 0;
        validYCoord = 0;
        invalidXCoord = -1;
        invalidYCoord = -1;
        testMap = new Map();
        validTestPoint = new Point(validXCoord, validYCoord);
        invalidTestPoint = new Point(invalidXCoord, invalidYCoord);
        invalidXPoint = new Point(invalidXCoord, validYCoord);
        invalidYPoint = new Point(validXCoord, invalidYCoord);
        testCellInBounds = new Cell(validTestPoint, testMap);
        testCellOutOfBounds = new Cell(invalidTestPoint, testMap);
        testCellXOutOfBounds = new Cell(invalidXPoint, testMap);
        testCellYOutOfBounds = new Cell(invalidYPoint, testMap);
        defaultEntity = Entity.NO_ENTITY;
    }

    @Test
    public void testConstructorSetsXCoordinate() {
        assertEquals(validXCoord, testCellInBounds.X);
    }
    
    @Test
    public void testConstructorSetsYCoordinate() {
        assertEquals(validYCoord, testCellInBounds.Y);
    }
    
    @Test
    public void testdGetParentMapReturnsMap() {
        assertEquals(testMap, testCellInBounds.getParentMap());
    }

    @Test
    public void testConstructorSetsDefaultEntity() {
        assertEquals(defaultEntity, testCellInBounds.getEntity());
    }
    
    @Test
    public void testSetAndGetEntity() {
        testCellInBounds.setEntity(Entity.ROOM);
        assertEquals(Entity.ROOM, testCellInBounds.getEntity());
    }
    
    @Test
    public void testIsFilledReturnsTrueWhenFilled() {
        testCellInBounds.setEntity(Entity.ROOM);
        assertTrue(testCellInBounds.isFilled());
    }
    
    @Test
    public void testIsFilledReturnsFalseWhenNotFilled() {
        assertFalse(testCellInBounds.isFilled());
    }
    
    @Test
    public void testIsRoomReturnsTrueWhenEntityIsRoomInBounds() {
        testCellInBounds.setEntity(Entity.ROOM);
        assertTrue(testCellInBounds.isRoom());
    }
    
    @Test
    public void testIsRoomReturnsFalseWhenEntityIsRoomOutOfBounds() {
        testCellOutOfBounds.setEntity(Entity.ROOM);
        assertFalse(testCellOutOfBounds.isRoom());
    }
    
    @Test
    public void testIsRoomReturnsFalseWhenEntityIsNotRoomInBounds() {
        assertFalse(testCellInBounds.isRoom());
    }
    
    @Test
    public void testIsRoomReturnsFalseWhenEntityIsNotRoomAndOutOfBounds() {
        assertFalse(testCellOutOfBounds.isRoom());
    }
    
    @Test
    public void testIsRoomReturnsFalseWhenEntityIsRoomWithXOutOfBounds() {
        testCellXOutOfBounds.setEntity(Entity.ROOM);
        assertFalse(testCellXOutOfBounds.isRoom());
    }
    
    @Test
    public void testIsRoomReturnsFalseWhenEntityIsRoomWithYOutOfBounds() {
        testCellYOutOfBounds.setEntity(Entity.ROOM);
        assertFalse(testCellYOutOfBounds.isRoom());
    }
    
}
