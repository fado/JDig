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

import data.Cell;
import data.Level;
import gui.CellPanel;
import gui.commands.Exit;
import gui.commands.Load;
import gui.commands.Save;
import gui.toolbars.maptools.MapTool;
import gui.toolbars.maptools.RoomTool;
import gui.toolbars.maptools.SelectionTool;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {

    private RoomTool roomTool;

    public MenuBar(Level level, final SelectionTool selectionTool, final RoomTool roomTool) {
        this.roomTool = roomTool;

        JMenu fileMenu = new JMenu("File");

        UIManager.getDefaults().put("Button.showMnemonics", Boolean.TRUE);

        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(new MenuActionListener(new Load()));
        open.setMnemonic(KeyEvent.VK_O);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        fileMenu.add(open);

        JMenuItem save = new JMenuItem("Save As...");
        save.addActionListener(new MenuActionListener( new Save(level)));
        save.setMnemonic(KeyEvent.VK_S);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
        fileMenu.add(save);

        fileMenu.addSeparator();
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new MenuActionListener( new Exit(level)));
        exit.setMnemonic(KeyEvent.VK_X);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        fileMenu.add(exit);

        this.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");

        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(selectionTool.getSelectedPanels().isEmpty()) {
                    deleteRoom(selectionTool.getCurrentPanel());
                } else {
                    for(CellPanel cellPanel : selectionTool.getSelectedPanels()) {
                        deleteRoom(cellPanel);
                    }
                }
            }
        });
        delete.setMnemonic(KeyEvent.VK_D);
        delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        editMenu.add(delete);

        this.add(editMenu);

        this.setVisible(true);
    }

    private void deleteRoom(CellPanel cellPanel) {
        Cell cell = cellPanel.getCell();
        roomTool.deleteRoom(cell, cellPanel);
    }
}
