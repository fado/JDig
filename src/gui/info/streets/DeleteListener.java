package gui.info.streets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;

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

public class DeleteListener implements ActionListener {

    private final StreetEditor editor;

    public DeleteListener(StreetEditor editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JList list = editor.getList();
        DefaultListModel listModel = editor.getListModel();
        JButton button = editor.getDeleteButton();
        int index = list.getSelectedIndex();
        listModel.remove(index);

        if (listModel.getSize() == 0) {
            button.setEnabled(false);
        } else {
            if (index == listModel.getSize()) {
                index--;
            }
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }
}
