package main;

import java.awt.Point;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DirectionTest {
    
    Point northOffset, southOffset, eastOffset, westOffset, northwestOffset,
        northeastOffset, southwestOffset, southeastOffset, testPoint;
    Cell testCell;
    
    @Before
    public void setUp() {
        northOffset = new Point(0, -1);
        southOffset = new Point(0, 1);
        eastOffset = new Point(1, 0);
        westOffset = new Point(-1, 0);
        northwestOffset = new Point(-1, -1);
        northeastOffset = new Point(1, -1);
        southwestOffset = new Point(-1, 1);
        southeastOffset = new Point(1, 1);
        
        testPoint = new Point(1, 1);
        testCell = new Cell(testPoint, null);
    }
    
    /**
     * Hits methods generated in the bytecode to show 100% coverage.
     */
    @Test
    public void superficialCodeCoverage() {
        Direction.valueOf(Direction.NORTH.toString());
    }
    
    @Test
    public void testNorthXOffset() {
        assertEquals(northOffset.x, Direction.NORTH.xOffset);
    }
    
    @Test
    public void testNorthXTranslation() {
        int expected = northOffset.x + testCell.X;
        int actual = Direction.NORTH.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNorthYOffset() {
        assertEquals(northOffset.y, Direction.NORTH.yOffset);
    }
    
    @Test
    public void testNorthYTranslation() {
        int expected = northOffset.y + testCell.Y;
        int actual = Direction.NORTH.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSouthXOffset() {
        assertEquals(southOffset.x, Direction.SOUTH.xOffset);
    }
    
    @Test
    public void testSouthXTranslation() {
        int expected = southOffset.x + testCell.X;
        int actual = Direction.SOUTH.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSouthYOffset() {
        assertEquals(southOffset.y, Direction.SOUTH.yOffset);
    }
    
    @Test
    public void testSouthYTranslation() {
        int expected = southOffset.y + testCell.Y;
        int actual = Direction.SOUTH.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testWestXOffset() {
        assertEquals(westOffset.x, Direction.WEST.xOffset);
    }
    
    @Test
    public void testWestXTranslation() {
        int expected = westOffset.x + testCell.X;
        int actual = Direction.WEST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testWestYOffset() {
        assertEquals(westOffset.y, Direction.WEST.yOffset);
    }
    
    @Test
    public void testWestYTranslation() {
        int expected = westOffset.y + testCell.Y;
        int actual = Direction.WEST.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testEastXOffset() {
        assertEquals(eastOffset.x, Direction.EAST.xOffset);
    }
    
    @Test
    public void testEastXTranslation() {
        int expected = eastOffset.x + testCell.X;
        int actual = Direction.EAST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testEastYOffset() {
        assertEquals(eastOffset.y, Direction.EAST.yOffset);
    }
    
    @Test
    public void testEastYTranslation() {
        int expected = eastOffset.y + testCell.Y;
        int actual = Direction.EAST.translateY(testCell);
        assertEquals(expected,actual);
    }
    
    @Test
    public void testNorthwestXOffset() {
        assertEquals(northwestOffset.x, Direction.NORTHWEST.xOffset);
    }
    
    @Test
    public void testNorthwestXTranslation() {
        int expected = northwestOffset.x + testCell.X;
        int actual = Direction.NORTHWEST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNorthwestYOffset() {
        assertEquals(northwestOffset.y, Direction.NORTHWEST.yOffset);
    }
    
    @Test
    public void testNorthwestYTranslation() {
        int expected = northwestOffset.y + testCell.Y;
        int actual = Direction.NORTHWEST.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNortheastXOffset() {
        assertEquals(northeastOffset.x, Direction.NORTHEAST.xOffset);
    }
    
    @Test
    public void testNortheastXTranslation() {
        int expected = northeastOffset.x + testCell.X;
        int actual = Direction.NORTHEAST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNortheastYOffset() {
        assertEquals(northeastOffset.y, Direction.NORTHEAST.yOffset);
    }
    
    @Test
    public void testNortheastYTranslation() {
        int expected = northwestOffset.y + testCell.Y;
        int actual = Direction.NORTHEAST.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSouthwestXOffset() {
        assertEquals(southwestOffset.x, Direction.SOUTHWEST.xOffset);
    }
    
    @Test
    public void testSouthwestXTranslation() {
        int expected = southwestOffset.x + testCell.X;
        int actual = Direction.SOUTHWEST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSouthwestYOffset() {
        assertEquals(southwestOffset.y, Direction.SOUTHWEST.yOffset);
    }
    
    @Test
    public void testSouthwestYTranslation() {
        int expected = southwestOffset.y + testCell.Y;
        int actual = Direction.SOUTHWEST.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSoutheastXOffset() {
        assertEquals(southeastOffset.x, Direction.SOUTHEAST.xOffset);
    }
    
    @Test
    public void testSoutheastXTranslation() {
        int expected = southeastOffset.x + testCell.X;
        int actual = Direction.SOUTHEAST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSoutheastYOffset() {
        assertEquals(southeastOffset.y, Direction.SOUTHEAST.yOffset);
    }
    
    @Test
    public void testSoutheastYTranslation() {
        int expected = southeastOffset.y + testCell.Y;
        int actual = Direction.SOUTHEAST.translateY(testCell);
        assertEquals(expected, actual);
    }
}
