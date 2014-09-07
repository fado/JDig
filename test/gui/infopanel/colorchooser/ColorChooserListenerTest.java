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
import gui.infopanel.InfoPanel;
import gui.levelpanel.CellPanel;
import main.BindingService;
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import static org.junit.Assert.assertTrue;

public class ColorChooserListenerTest {

    Level level;
    InfoPanel infoPanel;
    RoomColorSetter roomColorSetter;
    StreetColorSetter streetColorSetter;
    ColorChooserListener colorChooserListener;
    ColorChooser colorChooser;
    MouseEvent testEvent;
    JLabel colorChooserButton;
    CellPanel cellPanel, cellPanel2;
    Cell cell1, cell2;
    TestingUtils testingUtils = new TestingUtils();
    BindingService bindingService;

    @Before
    public void setUp() {
        level = new Level();
        level = testingUtils.populateLevel(2, 2, level);
        bindingService = new BindingService(level);
        bindingService = testingUtils.setupBindingService(bindingService, level);
        infoPanel = new InfoPanel(level, bindingService);
        colorChooser = new MockColorChooser();
        roomColorSetter = new RoomColorSetter(bindingService);
        streetColorSetter = new StreetColorSetter(new MockColorStreetDialog(), bindingService);
        colorChooserListener = new ColorChooserListener(infoPanel, colorChooser,
                roomColorSetter, streetColorSetter);
        colorChooserButton = new JLabel();
        testEvent = new MouseEvent(colorChooserButton, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false);

        cell1 = level.getCellAt(new Point(0, 0));
        cellPanel = bindingService.getBoundCellPanel(cell1);
        cell1.setEntity(new Room(cell1));

        cell2 = level.getCellAt(new Point(1, 1));
        cellPanel2 = bindingService.getBoundCellPanel(cell2);
        cell2.setEntity(new Room(cell2));
    }

    @Test
    public void testMouseClickedColorsAllSelectedRooms() {
        infoPanel.load(cell1);
        infoPanel.load(cell2);
        colorChooserListener.mouseClicked(testEvent);
        assertTrue(cell1.getColor() == Color.BLACK);
        assertTrue(cell2.getColor() == Color.BLACK);
    }

    @Test
    public void testMouseClickedWillColorStreet() {
        Street street = new Street("foo");
        level.addStreet(street);

        Room room = (Room)bindingService.getBoundCell(cellPanel).getEntity();
        room.setStreetName("foo");
        street.addRoom(room);

        Room room2 = (Room)bindingService.getBoundCell(cellPanel2).getEntity();
        room2.setStreetName("foo");
        street.addRoom(room2);

        infoPanel.load(cell1);
        colorChooserListener.mouseClicked(testEvent);

        assertTrue(cell1.getColor() == Color.BLACK);
        assertTrue(cell2.getColor() == Color.BLACK);
    }

}

class MockColorChooser implements ColorChooser {
    @Override
    public Color showDialog() {
        return Color.BLACK;
    }
}

class MockColorStreetDialog implements ColorDialog {
    @Override
    public int showDialog(String message) {
        return JOptionPane.YES_OPTION;
    }
}
