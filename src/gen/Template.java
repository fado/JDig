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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * An LPC file template.
 */
public class Template {

    private List<String> lines = new ArrayList<>();

    /**
     * Loads the template file at the passed-in path.
     * @param path Path to the template file.
     * @throws IOException
     */
    public void load(String path) throws IOException {
        lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
    }

    /**
     * Returns the contents of the template file as a List of Strings.
     * @return the contents of the template file as a List of Strings
     */
    public String getLines() {
        String linesAsString = "";
        for(String line : this.lines) {
            linesAsString += line + "\n";
        }
        return linesAsString;
    }

}
