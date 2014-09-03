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

import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Document listener for the street name field.
 */
public class StreetNameDocListener implements DocumentListener {

    private StreetEditor editor;

    /**
     * Constructor.
     * @param editor The editor in which the street name field is instantiated.
     */
    public StreetNameDocListener(StreetEditor editor) {
        this.editor = editor;
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
        JButton addButton = editor.getAddButton();
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
        JButton addButton = editor.getAddButton();
        if (streetFieldIsEmpty()) {
            addButton.setEnabled(false);
        } else {
            enableAddButton();
        }
    }

    /**
     * Determines whether or not the StreetName field is empty.
     * @return true if empty, otherwise false.
     */
    private boolean streetFieldIsEmpty() {
        return editor.getStreetNameField().getText().length() <= 0;
    }

    /**
     * Enable the AddButton if it is not enabled already.
     */
    private void enableAddButton() {
        JButton addButton = editor.getAddButton();
        if (!addButton.isEnabled()) {
            addButton.setEnabled(true);
        }
    }
}
