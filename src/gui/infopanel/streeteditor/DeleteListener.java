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


import main.BindingService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

/**
 * Allows for the deletion of StreetNames from the list.
 */
public class DeleteListener implements ActionListener {

    private final StreetEditor editor;
    private BindingService bindingService;

    public DeleteListener(StreetEditor editor, BindingService bindingService) {
        this.editor = editor;
        this.bindingService = bindingService;
    }

    /**
     * Determines the action performed when the DeleteButton is pressed.
     * @param event The event originating the call.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // Get fresh data.
        JList list = editor.getList();
        DefaultListModel listModel = editor.getListModel();
        JButton deleteButton = editor.getDeleteButton();
        int selectedIndex = list.getSelectedIndex();
        // Remove the street from the Level object.
        String elementAtIndex = listModel.getElementAt(selectedIndex).toString();
        bindingService.removeStreet(bindingService.getStreet(elementAtIndex));
        // And remove it from the list model.
        listModel.remove(selectedIndex);
        // If the list model is empty, disable the delete button.
        if (listModel.getSize() == 0) {
            deleteButton.setEnabled(false);
        } else {
            // If the selected index has gone out of bounds, bring it back one.
            if (selectedIndex == listModel.getSize()) {
                selectedIndex--;
            }
            // Now set it, then make sure the user can see it.
            list.setSelectedIndex(selectedIndex);
            list.ensureIndexIsVisible(selectedIndex);
        }
    }

}
