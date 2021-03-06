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
import gen.LpcGenerator;
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
    static final Logger logger = LoggerFactory.getLogger(InfoToolbar.class);
    private final GenerationDialog generationMessage;
    private String templatePath;
    private String destinationPath;

    /**
     * Constructor.
     * @param level The currently loaded level.
     * @param generationMessage Wrapper class for messages generated by the Listener.
     * @param templatePath The path of the template file.
     * @param destinationPath The path to the folder in which the LPC files should be
     *                        written.
     */
    public GenerationListener(Level level, GenerationDialog generationMessage,
                              String templatePath, String destinationPath) {
        this.level = level;
        this.generationMessage = generationMessage;
        this.templatePath = templatePath;
        this.destinationPath = destinationPath;
    }

    /**
     * Action performed by the Listener.  Checks to make sure that there are no
     * rooms without names.  If there are, an error is displayed.
     * @param actionEvent The event originating the call.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(roomsExistsWithoutName()) {
            generationMessage.showDialog(localization.get("StreetNamesNotEmpty"),
                    localization.get("WarningTitle"),
                    JOptionPane.WARNING_MESSAGE);
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
        LpcGenerator writer = new LpcGenerator(templatePath, destinationPath);
        for(Room room : level.getRooms()) {
            try {
                writer.generate(room);
            } catch (IOException ex) {
                logger.error(ex.toString());
            }
        }
        generationMessage.showDialog(localization.get("LPCGeneratedMessage"),
                localization.get("InfoTitle"),
                JOptionPane.INFORMATION_MESSAGE);
    }

}
