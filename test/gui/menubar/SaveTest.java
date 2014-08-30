package gui.menubar;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers. Copyright (C) 2014 Fado@Epitaph.
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
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import javax.swing.JOptionPane;
import static org.junit.Assert.assertTrue;

public class SaveTest {

    Level level;
    ExitCommand exitCommand;
    LoadDialog loadDialog;
    Command saveCommand;

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUp() {
        level = new Level(1, 1);
        loadDialog = new MockLoadDialog();
        saveCommand = new MockSaveCommand(level);
        exitCommand = new ExitCommand(level, loadDialog, (SaveCommand)saveCommand);
    }

    @Test
    public void testSystemExitForNoSelected() {
        exit.expectSystemExitWithStatus(0);
        level.registerRoom(new Room(null));
        MockLoadDialog mockLoadDialog = (MockLoadDialog)loadDialog;
        mockLoadDialog.setOption(JOptionPane.NO_OPTION);
        exitCommand.execute();
    }

    @Test
    public void testSystemExitWithNoRoomsInLevel() {
        exit.expectSystemExitWithStatus(0);
        exitCommand.execute();
    }

    @Test
    public void testSaveCommandExecuted() {
        level.registerRoom(new Room(null));
        MockLoadDialog mockLoadDialog = (MockLoadDialog)loadDialog;
        mockLoadDialog.setOption(JOptionPane.YES_OPTION);
        exitCommand.execute();
        MockSaveCommand mockSaveCommand = (MockSaveCommand)saveCommand;
        assertTrue(mockSaveCommand.saveExecuted());
    }

}

/**
 * Mock LoadDialog.
 */
class MockLoadDialog implements LoadDialog {

    private int option;

    @Override
    public int showDialog() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
}

/**
 * Mock SaveCommand.
 */
class MockSaveCommand extends SaveCommand {

    private boolean saveExecuted = false;

    public MockSaveCommand(Level level) {
        super(level);
    }

    @Override
    public void execute() {
        this.saveExecuted = true;
    }

    public boolean saveExecuted() {
        return this.saveExecuted;
    }
}
