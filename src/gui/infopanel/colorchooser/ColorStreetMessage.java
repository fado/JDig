package gui.infopanel.colorchooser;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers.
 * Copyright (C) 2014 Fado@Epitaph.
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

import properties.Localization;
import javax.swing.JOptionPane;

/**
 * Displays a JOptionPane that determines whether or not a selected
 * color should be propagated through all Rooms in a Street.
 */
public class ColorStreetMessage implements ColorDialog {

    Localization localization = new Localization();

    /**
     * Shows a dialog which determines whether or not a selected color
     * should be propagated through all Rooms in a Street.
     * @param message The message to be displayed by the dialog.
     * @return an integer representing yes or no, as defined in JOptionPane.
     */
    @Override
    public int showDialog(String message) {
        return JOptionPane.showOptionDialog(null,
                message,
                localization.get("SelectOptionTitle"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
    }

}
