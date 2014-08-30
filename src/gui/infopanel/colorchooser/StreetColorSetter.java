package gui.infopanel.colorchooser;

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

import data.Room;
import data.Street;
import properties.Localization;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * Sets the color of all Rooms in a Street.
 */
public class StreetColorSetter {

    ColorDialog colorDialog;
    Localization localization = new Localization();

    /**
     * Constructor.
     * @param colorDialog The dialog to be displayed by the StreetColorSetter.
     */
    public StreetColorSetter(ColorDialog colorDialog) {
        this.colorDialog = colorDialog;
    }

    /**
     * Sets an entire Street to the same color.
     */
    public void colorStreet(Street street, Color color) {
        int value = colorDialog.showDialog(localization.get("PropagateColorMessage"));
        if (value == JOptionPane.YES_OPTION) {
            for (Room room : street.getRooms()) {
                room.getCellPanel().setBackground(color);
                room.getCellPanel().getCell().setColor(color);
            }
        }
    }
}
