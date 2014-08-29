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
import gui.MessageDialog;
import properties.Localization;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

/**
 * Allows for the addition of street names to the list.
 */
class AddListener implements ActionListener {

    private final StreetEditor editor;
    private final Level level;
    private final Localization localization = new Localization();
    private final String ALREADY_EXISTS_MSG = localization.get("AlreadyExistsMessage");
    private final String NOT_EMPTY_MSG = localization.get("NotEmptyMessage");
    private final MessageDialog messageDialog;

    public AddListener(StreetEditor editor, MessageDialog messageDialog) {
        this.editor = editor;
        this.level = editor.getLevel();
        this.messageDialog = messageDialog;
    }

    /**
     * Determines the action performed when the AddButton is pressed.
     * @param event The event originating the call.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        JTextField streetNameField = editor.getStreetNameField();
        String name = streetNameField.getText();
        // Ensure we're getting the latest ListModel.
        DefaultListModel<String> listModel = editor.getListModel();
        // Check we're not adding a duplicate.
        if (listModel.contains(name)) {
            messageDialog.showDialog(ALREADY_EXISTS_MSG, editor);
            return;
        // Check we're not adding a street name with no characters.
        } else if (streetFieldIsEmpty()) {
            messageDialog.showDialog(NOT_EMPTY_MSG, editor);
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
     * Determines whether or not the StreetName field is empty.
     * @return true if empty, otherwise false.
     */
    private boolean streetFieldIsEmpty() {
        return editor.getStreetNameField().getText().length() <= 0;
    }

}
