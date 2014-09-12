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

import data.Exit;
import data.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;

/**
 * Formats Strings for inclusion in the final LPC file write.
 */
public class StringFormatter {

    static final Logger logger = LoggerFactory.getLogger(StringFormatter.class);

    /**
     * Generates a String based on the Exits contained in the Room.
     * @param room The Room for which you are generating Exits.
     * @return a String based on the Exits contained in the Room.
     */
    protected String getExitString(Room room) {
        String exits = "";
        logger.info("Generating exit strings...");
        for (Exit exit : room.getExits()) {
            exits += MessageFormat.format(
                    "add_exit (\"{0}\", __DIR__ +\"{1}\", \"{2}\");\n    ",
                    exit.getDirection().toString().toLowerCase(),
                    exit.getDestination().getName(),
                    exit.getExitType().toString().toLowerCase()
            );
        }
        logger.info("Exits generated.");
        return exits;
    }

    /**
     * Generates a starting output String based on the template file
     * defined in config.properties.
     * @return a starting output String based on the template file.
     * @throws java.io.IOException
     */
    protected String getTemplateString(String templatePath) throws IOException {
        logger.info("Loading template file...");
        List<String> lines = Files.readAllLines(Paths.get(templatePath),
                Charset.defaultCharset());
        logger.info("Got "+ lines.size() +" lines.");
        // Turn the List into a String and return it.
        String output = "";
        for(String line : lines) {
            output += line +"\n";
        }
        return output;
    }

}
