package gui.levelpanel;

import data.Cell;
import data.Connection;
import data.ConnectionType;
import data.Level;
import data.Room;
import org.junit.Before;
import org.junit.Test;
import properties.Images;

import java.awt.Point;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CellPanelTest {

    Level level;
    Room room;
    Cell cell, cell2;
    CellPanel cellPanel, selectedCellPanel;
    Images images = new Images();

    @Before
    public void setUp() {
        level = new Level(1, 1);
        cell = new Cell(new Point(0, 0), level);

        cell2 = new Cell(new Point(0, 0), level);
        room = new Room(null);
        cell2.setEntity(room);
        selectedCellPanel = new CellPanel(cell2);
        selectedCellPanel.select();
    }

    @Test
    public void testConstructorCallsRestoreWithEntity() {
        cell.setEntity(new Room(null));
        cellPanel = new CellPanel(cell);
        assertNotNull(cellPanel.getEntityImagePath());
    }

    @Test
    public void testConstructorDoesNotCallRestoreWithNoEntity() {
        cellPanel = new CellPanel(cell);
        assertNull(cellPanel.getEntityImagePath());
    }

    @Test
    public void testRestoreAddsRoomImageForRoom() {
        cell.setEntity(new Room(null));
        cellPanel = new CellPanel(cell);
        String expected = images.getImagePath("Room");
        String actual = cellPanel.getEntityImagePath();
        assertEquals(expected, actual);
    }

    @Test
    public void testRestoreAddsExitImageForExit() {
        cell.setEntity(new Connection(ConnectionType.VERTICAL));
        cellPanel = new CellPanel(cell);
        String expected = ConnectionType.VERTICAL.getPath();
        String actual = cellPanel.getEntityImagePath();
        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithEntityAddsImage() {
        Room room = new Room(null);
        cell.setEntity(room);
        cellPanel = new CellPanel(cell);
        cellPanel.select();
        String expected = room.getSelectedImage();
        String actual = cellPanel.getEntityImagePath();
        assertEquals(expected, actual);
    }

    @Test
    public void testSelectWithNoEntityDoesNotAddImage() {
        cellPanel = new CellPanel(cell);
        cellPanel.select();
        assertNull(cellPanel.getEntityImagePath());
    }

    @Test
    public void testDeselectRestoresNormalImage() {
        selectedCellPanel.deselect();
        String expected = room.getNormalImage();
        String actual = selectedCellPanel.getEntityImagePath();
        assertEquals(expected, actual);
    }
}
