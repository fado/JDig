package gui.menubar;

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

import gui.JdigDialog;
import properties.Localization;
import javax.swing.JOptionPane;

/**
 * Wrapper class for a JOptionPane to display a message prompting users to save
 * upon exiting the application.
 */
public class DefaultLoadDialog implements JdigDialog {

    Localization localization = new Localization();

    /**
     * Shows a JOptionPane and returns the response given by the user.
     * @return a response integer from JOptionPane.
     */
    @Override
    public int showDialog() {
        return JOptionPane.showOptionDialog(null, localization.get("ExitMess"),
                localization.get("SaveTitle"), JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
    }

}
