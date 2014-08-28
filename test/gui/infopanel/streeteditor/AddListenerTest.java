package gui.infopanel.streeteditor;

import data.Level;
import gui.infopanel.InfoPanel;
import org.junit.Before;
import org.junit.Test;
import properties.Localization;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Administrator on 28/08/2014.
 */
public class AddListenerTest {

    Level level;
    StreetEditor streetEditor;
    ActionListener addListener;
    ActionEvent testEvent;
    DefaultListModel<String> listModel;

    @Before
    public void setUp() {
        level = new Level(1, 1);
        InfoPanel infoPanel = new InfoPanel(level);
        streetEditor = new StreetEditor(infoPanel);
        addListener = new AddListener(streetEditor, new MockDialog());
        streetEditor.run();

        // Action Event
        Localization localization = new Localization();
        String addString = localization.get("AddString");
        testEvent = new ActionEvent(streetEditor, ActionEvent.ACTION_PERFORMED, addString);

        listModel = streetEditor.getListModel();
    }

    @Test
    public void testCannotAddDuplicateStreetName() {
        streetEditor.getStreetNameField().setText("foo");
        listModel.add(0, "foo");
        addListener.actionPerformed(testEvent);
        assertTrue(level.getStreets().isEmpty());
    }

    @Test
    public void testCannotAddEmptyStreetName() {
        addListener.actionPerformed(testEvent);
        assertTrue(level.getStreets().isEmpty());
    }

    @Test
    public void testValidInputAdded() {
        streetEditor.getStreetNameField().setText("foo");
        addListener.actionPerformed(testEvent);
        assertTrue(listModel.get(0).equalsIgnoreCase("foo"));
    }

    @Test
    public void testStreetAddedToLevel() {
        streetEditor.getStreetNameField().setText("foo");
        addListener.actionPerformed(testEvent);
        assertNotNull(level.getStreet("foo"));
    }

    @Test
    public void testStreetNameFieldZeroed() {
        streetEditor.getStreetNameField().setText("foo");
        addListener.actionPerformed(testEvent);
        assertTrue(streetEditor.getStreetNameField().getText().equalsIgnoreCase(""));
    }

}

/**
 * Mock dialog class. Passed to AddListener so the okay button in
 * the dialog doesn't need to be clicked during the test.
 */
class MockDialog implements MessageDialog {
    @Override
    public void showDialog(String message, StreetEditor editor) { }
}
