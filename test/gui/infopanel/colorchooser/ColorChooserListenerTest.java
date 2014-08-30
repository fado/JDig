package gui.infopanel.colorchooser;

import data.Cell;
import data.Level;
import data.Room;
import data.Street;
import gui.infopanel.InfoPanel;
import gui.levelpanel.CellPanel;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import static org.junit.Assert.assertFalse;
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

    @Before
    public void setUp() {
        level = new Level(1, 1);
        infoPanel = new InfoPanel(level);
        colorChooser = new MockColorChooser();
        roomColorSetter = new RoomColorSetter();
        streetColorSetter = new StreetColorSetter(new MockColorStreetDialog());
        colorChooserListener = new ColorChooserListener(infoPanel, colorChooser,
                roomColorSetter, streetColorSetter);
        colorChooserButton = new JLabel();
        testEvent = new MouseEvent(colorChooserButton, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false);

        Cell cell1 = new Cell(new Point(0, 0), level);
        cellPanel = new CellPanel(cell1);
        cell1.setEntity(new Room(cellPanel));

        Cell cell2 = new Cell(new Point(1, 1), level);
        cellPanel2 = new CellPanel(cell2);
        cell2.setEntity(new Room(cellPanel2));
    }

    @Test
    public void testMouseClickedColorsAllSelectedRooms() {
        infoPanel.load(cellPanel);
        infoPanel.load(cellPanel2);
        colorChooserListener.mouseClicked(testEvent);
        assertTrue(cellPanel.getCell().getColor() == Color.BLACK);
        assertTrue(cellPanel2.getCell().getColor() == Color.BLACK);
    }

    @Test
    public void testMouseClickedWillColorStreet() {
        Street street = new Street("foo");
        level.addStreet(street);

        Room room = (Room)cellPanel.getCell().getEntity();
        room.setStreetName("foo");
        street.addRoom(room);

        Room room2 = (Room)cellPanel2.getCell().getEntity();
        room2.setStreetName("foo");
        street.addRoom(room2);

        infoPanel.load(cellPanel);
        colorChooserListener.mouseClicked(testEvent);

        assertTrue(cellPanel.getCell().getColor() == Color.BLACK);
        assertTrue(cellPanel2.getCell().getColor() == Color.BLACK);
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
