package gui.infopanel;

import data.Cell;
import data.Level;
import data.Room;
import data.Street;
import gui.levelpanel.CellPanel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Administrator on 29/08/2014.
 */
public class InfoPanelTest {

    Level level;
    InfoPanel infoPanel;
    CellPanel cellPanel;
    Room room;

    @Before
    public void setUp() {
        level = new Level(1, 1);
        Cell cell = new Cell(new Point(0, 0), level);
        cellPanel = new CellPanel(cell);
        room = new Room(cellPanel);
        cell.setEntity(room);
        infoPanel = new InfoPanel(level);
    }

    @Test
    public void testLoadAndGetCurrentRoom() {
        infoPanel.load(cellPanel);
        assertEquals(room, infoPanel.getCurrentRoom());
    }

    @Test
    public void testUnload() {
        infoPanel.load(cellPanel);
        infoPanel.unload();
        assertNull(infoPanel.getCurrentRoom());
    }

    @Test
    public void testPopulateStreetNames() {
        level.addStreet(new Street("foo"));
        infoPanel.populateStreetNames();

        JComboBox streetNameField = getStreetNameField();
        if (streetNameField != null) {
            assertTrue(streetNameField.getItemCount() > 0);
        } else {
            fail();
        }
    }

    private JComboBox getStreetNameField() {
        // And so we embark on a magical journey.
        // Get the content panel.
        Component[] components = infoPanel.getComponents();
        JPanel contentPanel = null;
        for(Component component : components) {
            if(component.getName() != null
                    && component.getName().equals("contentPanel")) {
                contentPanel = (JPanel)component;
            }
        }
        // Get the streetNameField.
        Component[] internalComponents;
        JComboBox streetNameField = null;
        if (contentPanel != null) {
            internalComponents = contentPanel.getComponents();
            for(Component component : internalComponents) {
                if(component.getName() != null
                        && component.getName().equals("streetNameField")) {
                    streetNameField = (JComboBox)component;
                }
            }
        }
        return streetNameField;
    }
}
