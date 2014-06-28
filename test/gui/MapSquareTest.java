package gui;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MapSquareTest {
    
    private final String CONFIG_PROPERTIES = "./config/config.properties";
    Properties properties;
    MapSquare testSquare1, testSquare2, testSquare3, testSquare4;
    MapGrid testGrid;
    Point testPoint1, testPoint2, testPoint3, testPoint4;
    
    @Before
    public void setUp() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(CONFIG_PROPERTIES));
        testPoint1 = new Point(0, 0);
        testPoint2 = new Point(0, -1);
        testPoint3 = new Point(-1, 0);
        testPoint4 = new Point(-1, -1);
        testSquare1 = new MapSquare(testGrid, testPoint1.x, testPoint1.y);
        testSquare2 = new MapSquare(testGrid, testPoint2.x, testPoint2.y);
        testSquare3 = new MapSquare(testGrid, testPoint3.x, testPoint3.y);
        testSquare4 = new MapSquare(testGrid, testPoint4.x, testPoint4.y);
    }
    
    
    
    @Test
    public void testSizeCorrectlySetFromProperties() {
        int expected = Integer.parseInt(properties.getProperty("map_grid_size"));
        int actual = testSquare1.getPreferredSize().height;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGridCorrectlySet() {
        assertEquals(testGrid, testSquare1.getMapGrid());
    }
    
    @Test
    public void testPositionCorrectlySet() {
        assertEquals(testPoint1, testSquare1.getPosition());
    }
    
    @Test
    public void testSetRoomTrueAndIsRoomReturnsTrue() {
        testSquare1.setRoom(true);
        assertTrue(testSquare1.isRoom());
    }
    
    @Test
    public void testSetRoomFalseAndIsRoomReturnsFalse() {
        testSquare1.setRoom(false);
        assertFalse(testSquare1.isRoom());
    }
    
    @Test
    public void testSetExitTrueAndIsExitReturnsTrue() {
        testSquare1.setExit(true);
        assertTrue(testSquare1.isExit());
    }
    
    @Test
    public void testSetExitFalseAndIsExitReturnsFalse() {
        testSquare1.setExit(false);
        assertFalse(testSquare1.isExit());
    }
    
    @Test
    public void testIsFilledReturnsTrueWithRoom() {
        testSquare1.setRoom(true);
        assertTrue(testSquare1.isFilled());
    }
    
    @Test
    public void testIsFilledReturnsTrueWithExit() {
        testSquare1.setExit(true);
        assertTrue(testSquare1.isFilled());
    }
    
    @Test
    public void testIsFilledReturnsFalseWithNoExitOrRoom() {
        assertFalse(testSquare1.isFilled());
    }
    
    @Test
    public void testIsInBoundsReturnsTrueForValidXAndYPositions() {
        assertTrue(testSquare1.isInBounds());
    }
    
    @Test
    public void testIsInBoundsReturnsFalseForInvalidYPosition() {
        assertFalse(testSquare2.isInBounds());
    }
    
    @Test
    public void testIsInBoundsReturnsFalseForINvalidXPosition() {
        assertFalse(testSquare3.isInBounds());
    }
    
    @Test
    public void testIsInBoundsReturnsFalseForInvalidXAndYPositions() {
        assertFalse(testSquare4.isInBounds());
    }
    
}