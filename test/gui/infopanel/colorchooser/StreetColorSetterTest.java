package gui.infopanel.colorchooser;

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

import data.Cell;
import data.Level;
import data.Room;
import data.Street;
import gui.levelpanel.CellPanel;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Point;
import static org.junit.Assert.assertFalse;

public class StreetColorSetterTest {

    Level level;
    Street street;
    Cell cell;
    StreetColorSetter streetColorSetter;

    @Before
    public void setUp() {
        level = new Level(1, 1);
        street = new Street("foo");
        level.addStreet(street);
        cell = new Cell(new Point(0, 0), level);
        CellPanel cellPanel = new CellPanel(cell);
        Room room = new Room(cellPanel);
        cell.setEntity(room);
        street.addRoom(room);
        cell.setColor(Color.BLACK);

        streetColorSetter = new StreetColorSetter(new MockColorDialog());
    }

    @Test
    public void testNoOptionDoesNotChangeColor() {
        streetColorSetter.colorStreet(street, Color.WHITE);
        assertFalse(cell.getColor() == Color.WHITE);
    }
}

class MockColorDialog implements ColorDialog {

    @Override
    public int showDialog(String message) {
        return JOptionPane.NO_OPTION;
    }
}