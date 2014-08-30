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

import data.Level;
import javafx.stage.FileChooser;
import persistence.LevelSaver;
import properties.JdigProperties;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SaveCommand extends Command {

    private JdigProperties jdigProperties = new JdigProperties();
    private final String SAVE_FILE_LOCATION = jdigProperties.getProperty("SaveFileLocation");
    private Level level;
    private File file;
    private JFileChooser fileChooser;
    private LevelSaver levelSaver;

    public SaveCommand(Level level, JFileChooser fileChooser, LevelSaver levelSaver) {
        this.level = level;
        this.fileChooser = fileChooser;
        this.levelSaver = levelSaver;
    }

    @Override
    public void execute() {
        fileChooser.setCurrentDirectory(new File(SAVE_FILE_LOCATION));
        FileFilter filter = new FileNameExtensionFilter("XML File", "xml");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
        if (file != null) {
            levelSaver.save(level, file);
        }
    }

}
