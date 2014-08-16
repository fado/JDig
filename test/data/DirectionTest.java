package data;

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

import java.awt.Point;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DirectionTest {
    
    private Point northOffset;
    private Point southOffset;
    private Point eastOffset;
    private Point westOffset;
    private Point northwestOffset;
    private Point northeastOffset;
    private Point southwestOffset;
    private Point southeastOffset;
    private Cell testCell;
    
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
        Point testPoint = new Point(1, 1);
        testCell = new Cell(testPoint, null);
    }

    @Test
    public void testNorthXTranslation() {
        int expected = northOffset.x + testCell.X;
        int actual = Direction.NORTH.translateX(testCell);
        assertEquals(expected, actual);
    }

    @Test
    public void testNorthYTranslation() {
        int expected = northOffset.y + testCell.Y;
        int actual = Direction.NORTH.translateY(testCell);
        assertEquals(expected, actual);
    }

    @Test
    public void testSouthXTranslation() {
        int expected = southOffset.x + testCell.X;
        int actual = Direction.SOUTH.translateX(testCell);
        assertEquals(expected, actual);
    }

    @Test
    public void testSouthYTranslation() {
        int expected = southOffset.y + testCell.Y;
        int actual = Direction.SOUTH.translateY(testCell);
        assertEquals(expected, actual);
    }

    @Test
    public void testWestXTranslation() {
        int expected = westOffset.x + testCell.X;
        int actual = Direction.WEST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testWestYTranslation() {
        int expected = westOffset.y + testCell.Y;
        int actual = Direction.WEST.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testEastXTranslation() {
        int expected = eastOffset.x + testCell.X;
        int actual = Direction.EAST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testEastYTranslation() {
        int expected = eastOffset.y + testCell.Y;
        int actual = Direction.EAST.translateY(testCell);
        assertEquals(expected,actual);
    }
    
    @Test
    public void testNorthwestXTranslation() {
        int expected = northwestOffset.x + testCell.X;
        int actual = Direction.NORTHWEST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNorthwestYTranslation() {
        int expected = northwestOffset.y + testCell.Y;
        int actual = Direction.NORTHWEST.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNortheastXTranslation() {
        int expected = northeastOffset.x + testCell.X;
        int actual = Direction.NORTHEAST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNortheastYTranslation() {
        int expected = northwestOffset.y + testCell.Y;
        int actual = Direction.NORTHEAST.translateY(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSouthwestXTranslation() {
        int expected = southwestOffset.x + testCell.X;
        int actual = Direction.SOUTHWEST.translateX(testCell);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSouthwestYTranslation() {
        int expected = southwestOffset.y + testCell.Y;
        int actual = Direction.SOUTHWEST.translateY(testCell);
        assertEquals(expected, actual);
    }

    
    @Test
    public void testSoutheastXTranslation() {
        int expected = southeastOffset.x + testCell.X;
        int actual = Direction.SOUTHEAST.translateX(testCell);
        assertEquals(expected, actual);
    }

    @Test
    public void testSoutheastYTranslation() {
        int expected = southeastOffset.y + testCell.Y;
        int actual = Direction.SOUTHEAST.translateY(testCell);
        assertEquals(expected, actual);
    }

}
