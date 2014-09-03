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
import data.Street;
import gui.infopanel.InfoPanel;
import org.junit.Before;
import org.junit.Test;
import properties.Localization;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteListenerTest {

    Level level;
    StreetEditor streetEditor;
    ActionListener deleteListener;
    ActionEvent testEvent;
    DefaultListModel<String> listModel;
    JList list;
    JButton deleteButton;

    @Before
    public void setUp() {
        level = new Level(1, 1);
        InfoPanel infoPanel = new InfoPanel(level);
        streetEditor = new StreetEditor(infoPanel);
        streetEditor.run();

        deleteButton = streetEditor.getDeleteButton();

        // Action Event.
        Localization localization = new Localization();
        String deleteString = localization.get("DeleteString");
        testEvent = new ActionEvent(streetEditor, ActionEvent.ACTION_PERFORMED, deleteString);

        // Setup test data.
        listModel = streetEditor.getListModel();
        listModel.add(0, "foo");
        level.addStreet(new Street("foo"));
        list = streetEditor.getList();
        list.setSelectedIndex(0);

        deleteListener = new DeleteListener(streetEditor);
    }

    @Test
    public void testStreetRemovedFromLevel() {
        deleteListener.actionPerformed(testEvent);
        assertTrue(level.getStreets().isEmpty());
    }

    @Test
    public void testStreetRemovedFromListModel() {
        deleteListener.actionPerformed(testEvent);
        assertTrue(listModel.isEmpty());
    }

    @Test
    public void testDeleteButtonDisabledWithZeroListModelSize() {
        deleteListener.actionPerformed(testEvent);
        assertFalse(deleteButton.isEnabled());
    }

    @Test
    public void testDeleteButtonEnabledWithListModelSizeNotZero() {
        listModel.add(1, "bar");
        deleteListener.actionPerformed(testEvent);
        assertTrue(deleteButton.isEnabled());
    }

    @Test
    public void testSelectedIndexDecremented() {
        listModel.add(1, "bar");
        list.setSelectedIndex(1);
        deleteListener.actionPerformed(testEvent);
        assertEquals(0, list.getSelectedIndex());
    }

    @Test
    public void testSelectedIndexNotDecrementedWhenLessThanListSize() {
        listModel.add(1, "bar");
        listModel.add(2, "foo");
        listModel.add(3, "tux");
        list.setSelectedIndex(2);
        deleteListener.actionPerformed(testEvent);
        assertEquals(2, list.getSelectedIndex());
    }
}
