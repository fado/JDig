package gui.streeteditor;

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
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class AddListener implements ActionListener, DocumentListener {

    private final StreetEditor editor;
    private final Level level;
    private final Localization localization = new Localization();
    private final String ALREADY_EXISTS_MSG = localization.get("AlreadyExistsMessage");
    private final String NOT_EMPTY_MSG = localization.get("NotEmptyMessage");

    public AddListener(StreetEditor editor, Level level) {
        this.editor = editor;
        this.level = level;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JTextField streetNameField = editor.getStreetNameField();
        String name = streetNameField.getText();
        DefaultListModel listModel = editor.getListModel();

        if (listModel.contains(name)) {
            showDialog(ALREADY_EXISTS_MSG);
            return;
        } else if (streetFieldIsEmpty()) {
            showDialog(NOT_EMPTY_MSG);
            return;
        }

        listModel.insertElementAt(streetNameField.getText(), listModel.getSize());
        level.addStreet(new Street(streetNameField.getText()));
        streetNameField.requestFocusInWindow();
        streetNameField.setText("");
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        JButton button = editor.getAddButton();
        if (!button.isEnabled()) {
            button.setEnabled(true);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
        JButton button = editor.getAddButton();
        if (streetFieldIsEmpty()) {
            button.setEnabled(false);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
        JButton button = editor.getAddButton();
        if (streetFieldIsEmpty()) {
            button.setEnabled(false);
        } else {
            enableButton();
        }
    }

    private void showDialog(String message) throws HeadlessException {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null,
                message,
                localization.get("ErrorTitle"),
                JOptionPane.WARNING_MESSAGE);
        editor.getStreetNameField().requestFocusInWindow();
        editor.getStreetNameField().selectAll();
    }

    public boolean streetFieldIsEmpty() {
        JTextField streetField = editor.getStreetNameField();
        return streetField.getText().length() <= 0;
    }

    public void enableButton() {
        JButton button = editor.getAddButton();
        if (!button.isEnabled()) {
            button.setEnabled(true);
        }
    }
}
