package gui.infopanel.colorchooser;

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

/**
 * Created by Administrator on 30/08/2014.
 */
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