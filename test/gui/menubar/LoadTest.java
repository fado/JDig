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

import org.junit.Before;
import org.junit.Test;
import persistence.LevelLoader;
import javax.swing.JFileChooser;
import java.awt.Component;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoadTest {

    Load load;
    JFileChooser fileChooser;
    LevelLoader levelLoader;

    @Before
    public void setUp() {
        fileChooser = new MockFileChooser();
        levelLoader = new MockLevelLoader();
        load = new Load(fileChooser, levelLoader);
    }

    @Test
    public void testCancelLoad() {
        load.execute();
        MockLevelLoader loader = (MockLevelLoader)levelLoader;
        assertFalse(loader.fileLoaded());
    }

    @Test
    public void testApproveLoad() {
        MockFileChooser mockFileChooser = (MockFileChooser)fileChooser;
        mockFileChooser.setApprove(true);
        load.execute();
        MockLevelLoader loader = (MockLevelLoader)levelLoader;
        assertTrue(loader.fileLoaded());
    }

}

/**
 * Mock FileChooser.
 */
class MockFileChooser extends JFileChooser {

    private boolean approve = false;

    @Override
    public void setCurrentDirectory(File file) {}

    public void setFileFilter(FileFilter filter) {}

    @Override
    public int showOpenDialog(Component parent) {
        if(approve) {
            return JFileChooser.APPROVE_OPTION;
        }
        return JFileChooser.CANCEL_OPTION;
    }

    @Override
    public File getSelectedFile() {
        File file = null;
        try {
            file = File.createTempFile("test", ".tmp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void setApprove(boolean bool) {
        this.approve = bool;
    }

}

/**
 * Mock LevelLoader.
 */
class MockLevelLoader extends LevelLoader {

    private boolean fileLoaded = false;

    @Override
    public void load(File file) {
        fileLoaded = true;
    }

    public boolean fileLoaded() {
        return fileLoaded;
    }

}
