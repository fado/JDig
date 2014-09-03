package persistence;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers.  Copyright (C) 2014 Fado@Epitaph.
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

import data.Cell;
import data.Level;
import data.Room;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.Localization;
import javax.swing.JOptionPane;

/**
 * Uses the XStream library to convert a JDig Level object to XML.
 */
public class LevelSaver {

    private XStream xStream = new XStream();
    static final Logger logger = LoggerFactory.getLogger(LevelSaver.class);

    /**
     * Converts the passed-in Level to XML before passing it on to be written to
     * the passed in File.
     * @param level The Level to be converted to XML.
     * @param file The File the XML should be written to.
     */
    public void save(Level level, File file) {
        // Create the XML String from the Level.
        xStream.omitField(Cell.class, "level");
        xStream.omitField(Cell.class, "cellPanel");
        xStream.omitField(Room.class, "localization");
        xStream.omitField(Room.class, "images");
        xStream.omitField(Room.class, "cellPanel");
        String xml = xStream.toXML(level);
        // Write the file.
        write(xml, file);
        Localization localization = new Localization();
        JOptionPane.showMessageDialog(null, localization.get("SaveMessage"),
                localization.get("SaveTitle"), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Writes the passed-in XML String to the passed-in File.
     * @param xml The XML String to be written.
     * @param file The File to write the XML String to.
     */
    private void write(String xml, File file) {
        try {
            // Creating a new save file.
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));
            logger.info("Writing file: "+file.getPath());
            writer.write(xml);
            writer.flush();
        } catch (IOException ex) {
            logger.error(ex.toString());
        }
    }

}
