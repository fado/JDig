package gen;

/**
 * Jdig, a tool for the automatic generation of LPC class files for Epitaph
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

/**
 * Replaces Strings within the final LPC output string, marked by the
 * escape characters %..%.
 */
public class StringReplacer {

    /**
     * Replaces the exit marker in the room template with the exit String passed-in.
     * @param input The output string you want to add the exits too.
     * @param exitString The exit string you want to add.
     * @return The new output string with the exits added.
     */
    protected String replaceExitStrings(String input, String exitString) {
        return input.replace("%exit%", exitString);
    }

    /**
     * Replaces all the property markers in the output string with properties taken
     * from the Room file.
     * @param output The output string you want to add the properties too.
     * @param room The Room from which you want to take the properties.
     * @return The new output string with the properties added.
     */
    protected String replaceRoomStrings(String output, Room room) {
        output = output.replace("%include%", room.getInclude());
        output = output.replace("%inherit%", room.getInherit());
        output = output.replace("%short%", room.getShort());
        output = output.replace("%determinate%", room.getDeterminate());
        output = output.replace("%long%", room.getLong());
        output = output.replace("%light%", ""+room.getLight());
        return output;
    }

}
