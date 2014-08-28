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
import java.io.IOException;

/**
 * Takes a Room object and produces an LPC file.
 */
public class LpcGenerator {

    private String templatePath, destinationPath;

    public LpcGenerator(String templatePath, String destinationPath) {
        this.templatePath = templatePath;
        this.destinationPath = destinationPath;
    }

    /**
     * Generates an LPC file for the passed-in Room.
     * @param room The Room for which you want to generate an LPC file.
     * @throws IOException
     */
    public void generate(Room room) throws IOException {
        // Get the base output String.
        StringFormatter stringFormatter = new StringFormatter();
        String output = stringFormatter.getTemplateString(templatePath);
        // Replace the main room variable Strings.
        StringReplacer stringReplacer = new StringReplacer();
        output = stringReplacer.replaceRoomStrings(output, room);
        // Replace the exit Strings.
        output = stringReplacer.replaceExitStrings(output,
                stringFormatter.getExitString(room));
        // Write the file.
        LpcWriter lpcWriter = new LpcWriter();
        lpcWriter.write(output, room.getName(), destinationPath);
    }
    
}
