package gui.infotoolbar;

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
import data.Room;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import properties.JdigProperties;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.Properties;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenerationListenerTest {

    Level level;
    GenerationListener generationListener;
    MockGenerationMessage mockGenerationMessage;
    ActionEvent testEvent;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() {
        level = new Level(1, 1);
        mockGenerationMessage = new MockGenerationMessage();
        Properties jdigProperties = new JdigProperties();
        String templatePath = jdigProperties.getProperty("EpitaphTemplate");
        String destinationPath = folder.getRoot().getPath();
        generationListener = new GenerationListener(level, mockGenerationMessage,
                templatePath, destinationPath);
        testEvent = new ActionEvent(generationListener, ActionEvent.ACTION_PERFORMED, null);
    }

    @Test
    public void testRoomsWithNoNamesWillNotGenerate() {
        level.registerRoom(new Room(null));
        generationListener.actionPerformed(testEvent);
        assertFalse(mockGenerationMessage.lpcWasGenerated());
    }

    @Test
    public void testRoomsWithNamesWillGenerate() {
        Room room = new Room(null);
        room.setName("foo");
        level.registerRoom(room);
        generationListener.actionPerformed(testEvent);
        assertTrue(mockGenerationMessage.lpcWasGenerated());
    }
}

class MockGenerationMessage implements GenerationDialog {

    boolean lpcGenerated = false;

    @Override
    public void showDialog(String message, String title, int type) {
        if (type == JOptionPane.INFORMATION_MESSAGE) {
            lpcGenerated = true;
        }
    }

    public boolean lpcWasGenerated() {
        return lpcGenerated;
    }
}