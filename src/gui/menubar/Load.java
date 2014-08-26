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

import persistence.LevelPersistence;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.JdigProperties;

public class Load extends Command {

    private File file;
    private JdigProperties jdigProperties = new JdigProperties();
    private final String SAVE_FILE_LOCATION = jdigProperties.get("SaveFileLocation");
    static final Logger logger = LoggerFactory.getLogger(Load.class);

    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(SAVE_FILE_LOCATION));
        FileFilter filter = new FileNameExtensionFilter("XML File", "xml");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        if (file != null) {
            LevelPersistence levelPersistence = new LevelPersistence();
            try {
                levelPersistence.load(file);
            } catch (IOException ex) {
                logger.error(ex.toString());
            }
        }

    }

}
