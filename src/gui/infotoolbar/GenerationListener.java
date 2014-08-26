package gui.infotoolbar;

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

import data.Level;
import data.Room;
import gen.LpcWriter;
import properties.Localization;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ActionListener for the LPC Generation button.
 */
public class GenerationListener implements ActionListener {

    private Level level;
    private Localization localization = new Localization();
    private final String NOT_EMPTY_MESS = localization.get("StreetNamesNotEmpty");
    static final Logger logger = LoggerFactory.getLogger(InfoToolbar.class);

    public GenerationListener(Level level) {
        this.level = level;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(roomsExistsWithoutName()) {
            showNotEmptyMessage();
        } else {
            writeRooms();
        }
    }

    /**
     * Determines whether or not any Rooms within the Level do not have names.
     * @return true if a Room within the Level has no name, otherwise false.
     */
    private boolean roomsExistsWithoutName() {
        for(Room room : level.getRooms()) {
            if(room.getName().equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Invokes the LPC Writer to write the Room into an LPC file.
     */
    private void writeRooms() {
        LpcWriter writer = new LpcWriter();
        for(Room room : level.getRooms()) {
            try {
                writer.generate(room);
            } catch (IOException ex) {
                logger.error(ex.toString());
            }
        }
        showLpcGeneratedMessage();
    }

    /**
     * Shows a message warning the user that room names cannot be empty.
     */
    private void showNotEmptyMessage() {
        JOptionPane.showMessageDialog(null, NOT_EMPTY_MESS,
                localization.get("WarningTitle"), JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Shows a message telling the user that the LPC file has been generated.
     */
    private void showLpcGeneratedMessage() {
        JOptionPane.showMessageDialog(null, localization.get("LPCGeneratedMessage"),
                localization.get("InfoTitle"), JOptionPane.INFORMATION_MESSAGE);
    }
}
