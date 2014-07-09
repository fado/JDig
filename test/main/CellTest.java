
package main;

import java.awt.Point;
import main.entities.EntityType;
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
    
    @Before
    public void setUp() {
        validXCoord = 0;
        validYCoord = 0;
        invalidXCoord = -1;
        invalidYCoord = -1;
        testMap = new Map(1, 1);
        validTestPoint = new Point(validXCoord, validYCoord);
        invalidTestPoint = new Point(invalidXCoord, invalidYCoord);
        invalidXPoint = new Point(invalidXCoord, validYCoord);
        invalidYPoint = new Point(validXCoord, invalidYCoord);
        testCellInBounds = new Cell(validTestPoint, testMap);
        testCellOutOfBounds = new Cell(invalidTestPoint, testMap);
        testCellXOutOfBounds = new Cell(invalidXPoint, testMap);
        testCellYOutOfBounds = new Cell(invalidYPoint, testMap);
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
    public void testIsFilledReturnsTrueWhenFilled() {
        testCellInBounds.setEntityType(EntityType.ROOM);
        assertTrue(testCellInBounds.isFilled());
    }
    
    @Test
    public void testIsFilledReturnsFalseWhenNotFilled() {
        assertFalse(testCellInBounds.isFilled());
    }
    
    @Test
    public void testIsRoomReturnsTrueWhenEntityIsRoomInBounds() {
        testCellInBounds.setEntityType(EntityType.ROOM);
        assertTrue(testCellInBounds.isRoom());
    }
    
    @Test
    public void testIsRoomReturnsFalseWhenEntityIsRoomOutOfBounds() {
        testCellOutOfBounds.setEntityType(EntityType.ROOM);
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
        testCellXOutOfBounds.setEntityType(EntityType.ROOM);
        assertFalse(testCellXOutOfBounds.isRoom());
    }
    
    @Test
    public void testIsRoomReturnsFalseWhenEntityIsRoomWithYOutOfBounds() {
        testCellYOutOfBounds.setEntityType(EntityType.ROOM);
        assertFalse(testCellYOutOfBounds.isRoom());
    }
    
}
