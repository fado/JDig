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
import gui.CellPanel;
import gui.commands.Exit;
import gui.commands.Load;
import gui.commands.Save;
import gui.leveltools.DeleteEntity;
import gui.leveltools.RoomTool;
import gui.leveltools.SelectionTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import properties.JdigProperties;
import properties.Localization;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class MenuBar extends JMenuBar {

    private Level level;
    private SelectionTool selectionTool;
    private RoomTool roomTool;
    private Localization localization = new Localization();
    private final String DELETE_MESS = localization.get("DeleteMessage");
    static final Logger logger = LoggerFactory.getLogger(MenuBar.class);

    public MenuBar(Level level, final SelectionTool selectionTool, final RoomTool roomTool) {
        this.level = level;
        this.selectionTool = selectionTool;
        this.roomTool = roomTool;
        addComponentsToMenuBar(this);
    }

    private void addComponentsToMenuBar(JMenuBar menuBar) {
        UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);

        JMenu fileMenu = new JMenu(localization.get("File"));

        JMenuItem open = new JMenuItem(localization.get("Open"));
        open.addActionListener(new MenuActionListener(new Load()));
        open.setMnemonic(KeyEvent.VK_O);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileMenu.add(open);

        JMenuItem save = new JMenuItem(localization.get("SaveAs"));
        save.addActionListener(new MenuActionListener( new Save(level)));
        save.setMnemonic(KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
        fileMenu.add(save);

        fileMenu.addSeparator();
        JMenuItem exit = new JMenuItem(localization.get("Exit"));
        exit.addActionListener(new MenuActionListener( new Exit(level)));
        exit.setMnemonic(KeyEvent.VK_X);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        fileMenu.add(exit);

        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu(localization.get("Edit"));

        JMenuItem delete = new JMenuItem(localization.get("Delete"));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(selectionTool.getSelectedPanels().isEmpty()) {
                    if(showDeleteDialog() == JOptionPane.YES_OPTION) {
                        deleteRoom(selectionTool.getSelectedPanel());
                    }
                } else {
                    if(showDeleteDialog(selectionTool.getSelectedPanels().size())
                            == JOptionPane.YES_OPTION) {
                        for(CellPanel cellPanel : selectionTool.getSelectedPanels()) {
                            deleteRoom(cellPanel);
                        }
                    }
                }
            }
        });
        delete.setMnemonic(KeyEvent.VK_D);
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        editMenu.add(delete);

        menuBar.add(editMenu);

        JMenu helpMenu = new JMenu(localization.get("Help"));

        JMenuItem bugReport = new JMenuItem(localization.get("BugReport"));
        bugReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Desktop desktop = null;
                if (Desktop.isDesktopSupported()) {
                    desktop = Desktop.getDesktop();
                }
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    JdigProperties jdigProperties = new JdigProperties();
                    URL url;
                    try {
                        url = new URL(jdigProperties.get("BugReportURL"));
                        desktop.browse(url.toURI());
                    } catch (URISyntaxException | IOException ex) {
                        logger.error(ex.toString());
                    }

                }
            }
        });
        bugReport.setMnemonic(KeyEvent.VK_R);
        helpMenu.add(bugReport);

        menuBar.add(helpMenu);

        menuBar.setVisible(true);
    }

    private void deleteRoom(CellPanel cellPanel) {
        new DeleteEntity(cellPanel);
    }

    private int showDeleteDialog() {
        int option = JOptionPane.showOptionDialog(null, DELETE_MESS,
                localization.get("SelectOptionTitle"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null, null, null);
        return option;
    }

    private int showDeleteDialog(int numberOfRooms) {
        int option = JOptionPane.showOptionDialog(null,
                "Are you sure you want to delete "+ numberOfRooms +" rooms?",
                localization.get("SelectOptionTitle"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, null, null, null);
        return option;
    }

}
