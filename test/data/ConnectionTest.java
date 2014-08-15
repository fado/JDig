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

import java.awt.Color;

import static org.junit.Assert.assertEquals;

public class ConnectionTest {

    private ConnectionType testConnectionType, testConnectionType2;
    private Connection testConnection;

    @Before
    public void setUp() {
        testConnectionType = ConnectionType.NONE;
        testConnectionType2 = ConnectionType.HORIZONTAL;
        testConnection = new Connection(testConnectionType);
    }

    @Test
    public void testGetAndSetConnectionType() {
        testConnection.setConnectionType(testConnectionType2);
        assertEquals(testConnectionType2, testConnection.getConnectionType());
    }

    @Test
    public void testGetPath() {
        String expected = ConnectionType.VERTICAL.getPath();
        testConnection.setConnectionType(ConnectionType.VERTICAL);
        assertEquals(expected, testConnection.getPath());
    }

    @Test
    public void testNormalImageReturnsNulls() {
        assertEquals(null, testConnection.getNormalImage());
    }

    @Test
    public void testSelectedImageReturnsNull() {
        assertEquals(null, testConnection.getSelectedImage());
    }

}
