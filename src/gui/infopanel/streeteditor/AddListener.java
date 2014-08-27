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
import properties.Localization;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Allows for the addition of street names to the list.
 */
class AddListener implements ActionListener, DocumentListener {

    private final StreetEditor editor;
    private final Level level;
    private final JButton addButton;
    private final JTextField streetNameField;
    private final Localization localization = new Localization();
    private final String ALREADY_EXISTS_MSG = localization.get("AlreadyExistsMessage");
    private final String NOT_EMPTY_MSG = localization.get("NotEmptyMessage");

    public AddListener(StreetEditor editor) {
        this.editor = editor;
        this.level = editor.getLevel();
        this.addButton = editor.getAddButton();
        this.streetNameField = editor.getStreetNameField();
    }

    /**
     * Determines the action performed when the AddButton is pressed.
     * @param event The event originating the call.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String name = streetNameField.getText();
        // Ensure we're getting the latest ListModel.
        DefaultListModel<String> listModel = editor.getListModel();
        // Check we're not adding a duplicate.
        if (listModel.contains(name)) {
            showDialog(ALREADY_EXISTS_MSG);
            return;
        // Check we're not adding a street name with no characters.
        } else if (streetFieldIsEmpty()) {
            showDialog(NOT_EMPTY_MSG);
            return;
        }
        // Add the street name to the next available index of the list.
        listModel.add(listModel.getSize(), streetNameField.getText());
        // Register the new street with the Level object while we're at it.
        level.addStreet(new Street(streetNameField.getText()));
        // Now clear the text field.
        streetNameField.requestFocusInWindow();
        streetNameField.setText("");
    }

    /**
     * Enable to the AddButton in the StreetEditor if anything is entered into the
     * StreetName field.
     * @param event The event originating the call.
     */
    @Override
    public void insertUpdate(DocumentEvent event) {
        enableAddButton();
    }

    /**
     * Disable the AddButton if all text is removed from the StreetName field.
     * @param event The event originating the call.
     */
    @Override
    public void removeUpdate(DocumentEvent event) {
        if (streetFieldIsEmpty()) {
            addButton.setEnabled(false);
        }
    }

    /**
     * If the Document changes, check to see if the StreetName field is empty. If
     * it is, disable the AddButton, otherwise make sure it's still enabled.
     * @param event The event originating the call.
     */
    @Override
    public void changedUpdate(DocumentEvent event) {
        if (streetFieldIsEmpty()) {
            addButton.setEnabled(false);
        } else {
            enableAddButton();
        }
    }

    /**
     * Generic method for showing an error message.
     * @param message The error message to be shown.
     * @throws HeadlessException
     */
    private void showDialog(String message) throws HeadlessException {
        JOptionPane.showMessageDialog(null, message, localization.get("ErrorTitle"),
                JOptionPane.WARNING_MESSAGE);
        streetNameField.requestFocusInWindow();
        streetNameField.selectAll();
    }

    /**
     * Determines whether or not the StreetName field is empty.
     * @return true if empty, otherwise false.
     */
    private boolean streetFieldIsEmpty() {
        return streetNameField.getText().length() <= 0;
    }

    /**
     * Enable the AddButton if it is not enabled already.
     */
    private void enableAddButton() {
        if (!addButton.isEnabled()) {
            addButton.setEnabled(true);
        }
    }
}
