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
import org.junit.Before;
import org.junit.Test;
import persistence.LevelSaver;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SaveCommandTest {

    Level level = mock(Level.class);
    JFileChooser fileChooser = mock(JFileChooser.class);
    LevelSaver levelSaver = mock(LevelSaver.class);
    File tempFile, tempFile2;
    SaveCommand saveCommand;

    @Before
    public void setUp() throws IOException {
        tempFile = File.createTempFile("temp", ".xml");
        tempFile2 = File.createTempFile("temp", ".foo");
        saveCommand = new SaveCommand(level, fileChooser, levelSaver);
    }

    @Test
    public void testApproveGetsSelectedFile() {
        when(fileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        saveCommand.execute();
        verify(fileChooser).getSelectedFile();
    }

    @Test
    public void testCancelDoesNotGetSelectedFile() {
        when(fileChooser.showSaveDialog(null)).thenReturn(JFileChooser.CANCEL_OPTION);
        saveCommand.execute();
        verify(fileChooser, never()).getSelectedFile();
    }

    @Test
    public void testFileWithXmlSuffix() {
        when(fileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        when(fileChooser.getSelectedFile()).thenReturn(tempFile);
        saveCommand.execute();
        verify(levelSaver).save(level, tempFile);
    }

    @Test
    public void testFileWithoutXmlSuffix() {
        when(fileChooser.showSaveDialog(null)).thenReturn(JFileChooser.APPROVE_OPTION);
        when(fileChooser.getSelectedFile()).thenReturn(tempFile2);
        saveCommand.execute();
        verify(levelSaver).save(any(Level.class), any(File.class));
    }

}
