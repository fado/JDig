package gui;

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
import gui.commands.Exit;
import gui.menubar.MenuBar;
import gui.infopanel.InfoToolbar;
import gui.toolbars.MapToolbar;
import gui.infopanel.InfoPanel;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainUI implements Runnable {

    private final Level level;
    private MapToolbar toolbar;
    static final Logger logger = LoggerFactory.getLogger(MainUI.class);

    public MainUI(Level level) {
        this.level = level;
    }

    private void addComponentsToPane(Container pane) throws IOException {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;


        InfoPanel infoPanel = new InfoPanel(this.level);
        constraints.gridx = 1;
        constraints.gridy = 1;
        pane.add(infoPanel, constraints);

        InfoToolbar infoToolbar = new InfoToolbar(this.level);
        constraints.gridx = 1;
        constraints.gridy = 0;
        pane.add(infoToolbar, constraints);

        toolbar = new MapToolbar(infoPanel);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(toolbar, constraints);

        LevelPanel levelPanel = new LevelPanel(this.level, toolbar);
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(levelPanel, constraints);
        
        toolbar.setDefaultSelectionTool();
    }
  
    private void createAndShowGui() throws IOException {
        JFrame frame = new JFrame("JDig");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                new Exit(MainUI.this.level).execute();
            }
        });
        addComponentsToPane(frame.getContentPane());
        frame.setJMenuBar(new MenuBar(this.level, toolbar.getSelectionTool(),
                toolbar.getRoomTool()));
        frame.setVisible(true);
        frame.pack();
    }

    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            createAndShowGui();
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException |
                IOException ex) {
            logger.error(ex.toString());
        }
    }

}
