package gui.infopanel;

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
import data.Room;
import data.Street;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ActionListener for the StreetName field.
 */
public class StreetNameListener implements ActionListener {

    private List<Room> currentRooms;
    private Level level;
    private JComboBox streetNameField;

    public StreetNameListener(List<Room> currentRooms, Level level,
                              JComboBox streetNameField) {
        this.currentRooms = currentRooms;
        this.level = level;
        this.streetNameField = streetNameField;
    }

    /**
     * For each selected Room, sets the StreetName to the currently selected
     * items in the StreetName field.
     * @param actionEvent The event originating the call.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(!currentRooms.isEmpty()) {
            for(Room room : currentRooms) {
                // Set the street name in the Room object.
                room.setStreet((String) streetNameField.getSelectedItem());
                // Add the room to the Street in the Level object.
                Street street = level.getStreet(room.getStreet());
                if(street != null) {
                    street.addRoom(room);
                }
            }
        }
    }
}
