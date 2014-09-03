package gui.menubar;

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

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Filters the JFileChooers to only show XML files.
 */
public class XmlFileFilter extends FileFilter {

    /**
     * Returns true if the file is a directory or if the file suffix is .xml.
     * @param file The file under test.
     * @return true if the file is a directory or if the file suffix is .xml.
     */
    @Override
    public boolean accept(File file) {
        return file.isDirectory() || file.getName().toLowerCase().endsWith(".xml");
    }

    /**
     * Returns the description of the file type filtered for.
     * @return the description of the file type filtered for.
     */
    @Override
    public String getDescription() {
        return "Extensible Markup Language (*.XML)";
    }

}
