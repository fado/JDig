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
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.JdigProperties;

/**
 * Takes a Room object and produces an LPC file.
 */
public class LpcWriter {

    static final Logger logger = LoggerFactory.getLogger(LpcWriter.class);

    /**
     * Generates an LPC file for the passed-in Room.
     * @param room The Room for which you want to generate an LPC file.
     * @throws IOException
     */
    public void generate(Room room) throws IOException {
        String output = getTemplateString();
        output = replaceRoomStrings(output, room);
        output = replaceExitStrings(output, getExitString(room));
        write(output, room);
    }

    /**
     * Replaces the exit marker in the room template with the exit String passed-in.
     * @param output The output string you want to add the exits too.
     * @param exitString The exit string you want to add.
     * @return The new output string with the exits added.
     */
    private String replaceExitStrings(String output, String exitString) {
        output = output.replace("%exit%", exitString);
        return output;
    }

    /**
     * Replaces all the property markers in the output string with properties taken
     * from the Room file.
     * @param output The output string you want to add the properties too.
     * @param room The Room from which you want to take the properties.
     * @return The new output string with the properties added.
     */
    private String replaceRoomStrings(String output, Room room) {
        output = output.replace("%short%", room.getShort());
        output = output.replace("%determinate%", room.getDeterminate());
        output = output.replace("%long%", room.getLong());
        output = output.replace("%light%", ""+room.getLight());
        return output;
    }

    /**
     * Writes an LPC file.
     * @param output The output String containing the contents of the LPC file.
     * @param room The Room for which you are writing the LPC file.
     */
    private void write(String output, Room room) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./lpc/" + room.getName() + ".c"),
                    Charset.defaultCharset()));
            writer.write(output);
            writer.flush();
            logger.info("Writing LPC file for {0}", room.getName());
        } catch (IOException ex) {
            logger.error(ex.toString());
        }
    }

    /**
     * Generates a String based on the Exits contained in the Room.
     * @param room The Room for which you are generating Exits.
     * @return a String based on the Exits contained in the Room.
     */
    private String getExitString(Room room) {
        String exits = "";
        for (Exit exit : room.getExits()) {
            exits += MessageFormat.format(
                    "add_exit (\"{0}\", __DIR__ +\"{1}\", \"{2}\");\n    ",
                    exit.getDirection().toString().toLowerCase(),
                    exit.getDestination().getName() +".c",
                    exit.getExitType().toString().toLowerCase()
            );
        }
        return exits;
    }

    /**
     * Generates a starting output String based on the template file
     * defined in config.properties.
     * @return a starting output String based on the template file.
     * @throws IOException
     */
    private String getTemplateString() throws IOException {
        Template template = new Template();
        JdigProperties properties = new JdigProperties();
        template.load(properties.get("Template"));

        logger.info("Getting lines...");

        List<String> lines = template.getLines();
        String output = "";
        for(String string : lines) {
            output += string + "\n";
        }
        return output;
    }
    
}
