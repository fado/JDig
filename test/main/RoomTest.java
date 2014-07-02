package main;

import java.awt.Point;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {
    
    Point testPoint1, testPoint2;
    Room testRoom1, testRoom2;
    
    @Before
    public void setUp() {
        testPoint1 = new Point(1, 1);
        testPoint2 = new Point(2, 1);
        testRoom1 = new Room(testPoint1);
        testRoom2 = new Room(testPoint2);
    }

    @Test
    public void testConstructorSetsPositionAndGetterReturnsCorrectly() {
        assertEquals(testPoint1, testRoom1.getPosition());
    }
    
    @Test
    public void testConstructorInitializesHashMap() {
        assertNotNull(testRoom1.getExits());
        assertTrue(testRoom1.getExits().isEmpty());
    }
    
    @Test
    public void testAddExitAndGetExits() {
        testRoom1.addExit(Direction.EAST, testRoom2);
        assertTrue(testRoom1.getExits().containsKey("EAST"));
        assertTrue(testRoom1.getExits().containsValue(testRoom2));
    }
    
    @Test
    public void testToString() {
        String expected = "1, 1";
        String actual = testRoom1.toString();
        assertEquals(expected, actual);
    }
    
}
