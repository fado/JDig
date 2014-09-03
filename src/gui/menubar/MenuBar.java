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

import main.ApplicationFactory;
import properties.Localization;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * MenuBar for the application.
 */
public class MenuBar extends JMenuBar {

    private Localization localization = new Localization();

    /**
     * Constructor.
     */
    public MenuBar() {
        addComponentsToMenuBar(this);
    }

    /**
     * Adds components to the MenuBar.
     * @param menuBar The MenuBar to which the components should be added.
     */
    private void addComponentsToMenuBar(JMenuBar menuBar) {
        UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);

        // FILE MENU.
        JMenu fileMenu = new JMenu(localization.get("File"));

        // Open - Open a Level.
        JMenuItem open = new JMenuItem(localization.get("Open"));
        open.addActionListener(new MenuActionListener(ApplicationFactory
                .INSTANCE.getNewLoadCommand()));
        open.setMnemonic(KeyEvent.VK_O);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_MASK));
        fileMenu.add(open);

        // Save - Save a Level.
        JMenuItem save = new JMenuItem(localization.get("SaveAs"));
        save.addActionListener(new MenuActionListener(ApplicationFactory
                .INSTANCE.getNewSaveCommand()));
        save.setMnemonic(KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
        fileMenu.add(save);

        fileMenu.addSeparator();

        // Exit - Exit the application.
        JMenuItem exit = new JMenuItem(localization.get("Exit"));
        exit.addActionListener(new MenuActionListener(ApplicationFactory
                .INSTANCE.getNewExitCommand()));
        exit.setMnemonic(KeyEvent.VK_X);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                InputEvent.CTRL_MASK));
        fileMenu.add(exit);

        menuBar.add(fileMenu);

        // EDIT MENU.
        JMenu editMenu = new JMenu(localization.get("Edit"));

        // Delete - Delete a room or selected rooms.
        JMenuItem delete = new JMenuItem(localization.get("Delete"));
        delete.addActionListener(new MenuActionListener(ApplicationFactory
                .INSTANCE.getNewDeleteCommand()));
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
