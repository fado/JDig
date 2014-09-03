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

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import data.Level;
import gui.menubar.MenuBar;
import main.ApplicationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main user interface for the application.
 */
public class MainUI implements Runnable {

    static final Logger logger = LoggerFactory.getLogger(MainUI.class);
    private Level level;

    /**
     * Constructor.
     * @param level The currently loaded level.
     */
    public MainUI(Level level) {
        this.level = level;
    }

    /**
     * Adds the Components to the passed-in Container.  Components are added
     * in a grid bag layout.
     * @param pane The Container to which the Components should be added.
     * @throws IOException
     */
    private void addComponentsToPane(Container pane) throws IOException {
        pane = setupConstraints(pane);

        JdigComponent[] components = ApplicationFactory.INSTANCE.getGuiComponents();
        for(JdigComponent component : components) {
            pane.add((Component)component, component.getConstraints());
        }
    }

    /**
     * Sets up the GridBagConstraints for the passed-in Container.
     * @param pane The Container to which the GridBagConstraints are to be added.
     * @return The Container, with the added constraints.
     */
    private Container setupConstraints(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        return pane;
    }

    /**
     * Creates and displays the GUI.
     * @throws IOException
     */
    private void createAndShowGui() throws IOException {
        JFrame frame = new JFrame("JDig");
        ApplicationFactory.INSTANCE.buildApplication(level);
        addComponentsToPane(frame.getContentPane());
        // Setup the menu bar.
        frame.setJMenuBar(new MenuBar());
        // Set custom window close behaviour.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                ApplicationFactory.INSTANCE.getNewExitCommand().execute();
            }
        });
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Sets the interface to the system look-and-feel, then calls the methods
     * necessary to setup the GUI.
     */
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
