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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectionTypeTest {

    private String horizontalPath, verticalPath, forwardDiagonalPath,
        backwardDiagonalPath, xPath;

    @Before
    public void setUp() {
        horizontalPath = "./resources/images/horizontal_exit.png";
        verticalPath = "./resources/images/vertical_exit.png";
        forwardDiagonalPath = "./resources/images/forward_diagonal_exit.png";
        backwardDiagonalPath = "./resources/images/back_diagonal_exit.png";
        xPath = "./resources/images/x_exit.png";
    }

    @Test
    public void testGetHorizontalPath() {
        String expected = horizontalPath;
        String actual = ConnectionType.HORIZONTAL.getPath();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetVerticalPath() {
        String expected = verticalPath;
        String actual = ConnectionType.VERTICAL.getPath();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetForwardDiagonalPath() {
        String expected = forwardDiagonalPath;
        String actual = ConnectionType.FORWARD_DIAGONAL.getPath();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBackwardDiagonalPath() {
        String expected = backwardDiagonalPath;
        String actual = ConnectionType.BACKWARD_DIAGONAL.getPath();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetXPath() {
        String expected = xPath;
        String actual = ConnectionType.X.getPath();
        assertEquals(expected, actual);
    }

}
