package gen;

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

import data.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.JdigProperties;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * Writes an LPC file.
 */
public class LpcWriter {

    static final Logger logger = LoggerFactory.getLogger(LpcWriter.class);

    /**
     * Writes an LPC file.
     * @param output The output String containing the contents of the LPC file.
     * @param room The Room for which you are writing the LPC file.
     */
    public void write(String output, Room room) {
        try {
            JdigProperties jdigProperties = new JdigProperties();
            String destinationPath = jdigProperties.get("LpcOutputFolder");
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(destinationPath + room.getName() + ".c"),
                    Charset.defaultCharset()));
            writer.write(output);
            writer.flush();
            logger.info("Writing LPC file for {0}", room.getName());
        } catch (IOException ex) {
            logger.error(ex.toString());
        }
    }

}
