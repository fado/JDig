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
import persistence.LevelLoader;
import persistence.LevelSaver;
import properties.Localization;
import tools.SelectionTool;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    private Level level;
    private SelectionTool selectionTool;
    private Localization localization = new Localization();

    public MenuBar(Level level, SelectionTool selectionTool) {
        this.level = level;
        this.selectionTool = selectionTool;
        addComponentsToMenuBar(this);
    }

    private void addComponentsToMenuBar(JMenuBar menuBar) {
        UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);

        // FILE MENU.
        JMenu fileMenu = new JMenu(localization.get("File"));

        // Open - Open a Level.
        JMenuItem open = new JMenuItem(localization.get("Open"));
        open.addActionListener(new MenuActionListener(new LoadCommand(new JFileChooser(),
                new LevelLoader())));
        open.setMnemonic(KeyEvent.VK_O);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileMenu.add(open);

        // Save - Save a Level.
        JMenuItem save = new JMenuItem(localization.get("SaveAs"));
        save.addActionListener(new MenuActionListener( new SaveCommand(level, new JFileChooser(),
                new LevelSaver())));
        save.setMnemonic(KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
        fileMenu.add(save);

        fileMenu.addSeparator();

        // Exit - Exit the application.
        JMenuItem exit = new JMenuItem(localization.get("Exit"));
        exit.addActionListener(new MenuActionListener( new ExitCommand(level,
                new DefaultLoadDialog(), new SaveCommand(level, new JFileChooser(),
                new LevelSaver()))));
        exit.setMnemonic(KeyEvent.VK_X);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        fileMenu.add(exit);

        menuBar.add(fileMenu);

        // EDIT MENU.
        JMenu editMenu = new JMenu(localization.get("Edit"));

        // Delete - Delete a room or selected rooms.
        JMenuItem delete = new JMenuItem(localization.get("Delete"));
        delete.addActionListener(new MenuActionListener(new DeleteCommand(selectionTool)));
        delete.setMnemonic(KeyEvent.VK_D);
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        editMenu.add(delete);

        menuBar.add(editMenu);

        // HELP MENU.
        JMenu helpMenu = new JMenu(localization.get("Help"));

        // Bug report - Report a bug in the system.
        JMenuItem bugReport = new JMenuItem(localization.get("BugReport"));
        bugReport.addActionListener(new BugReportActionListener());
                bugReport.setMnemonic(KeyEvent.VK_R);
        helpMenu.add(bugReport);

        menuBar.add(helpMenu);

        menuBar.setVisible(true);
    }

}
