package gui.infopanel.streeteditor;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers. Copyright (C) 2014 Fado@Epitaph.
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

import data.Level;
import gui.infopanel.InfoPanel;
import main.BindingService;
import org.junit.Before;
import org.junit.Test;
import properties.Localization;
import utils.TestingUtils;

import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AddListenerTest {

    Level level;
    StreetEditor streetEditor;
    ActionListener addListener;
    ActionEvent testEvent;
    DefaultListModel<String> listModel;
    TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        level = new Level();
        testingUtils.populateLevel(1, 1, level);
        BindingService bindingService = new BindingService(level);
        bindingService = testingUtils.setupBindingService(bindingService, level);
        InfoPanel infoPanel = new InfoPanel(level, bindingService);
        streetEditor = new StreetEditor(infoPanel, bindingService);
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
class MockDialog implements EditorDialog {
    @Override
    public void showDialog(String message, StreetEditor editor) { }
}
