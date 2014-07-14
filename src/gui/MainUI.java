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

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import data.Level;

/**
 * The Gui class specifies the user interface for JDig.
 */
public class MainUI implements Runnable {

    private final Level map;
    
    public MainUI(Level map) {
        this.map = map;
    }

    /**
     * Adds components to the passed-in Container.
     *
     * @param pane - The Container to which the components are to be added.
     */
    private void addComponentsToPane(Container pane) throws IOException {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;

        InfoPanel infoPanel = new InfoPanel();
        constraints.gridx = 1;
        constraints.gridy = 1;
        pane.add(infoPanel, constraints);

        MapToolbar toolbar = new MapToolbar(infoPanel);
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(toolbar, constraints);
        
        
        LevelPanel mapPanel = new LevelPanel(this.map, toolbar);
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(mapPanel, constraints);
        
        toolbar.setDefaultSelectionTool();
    }
    
    /**
     * Creates a Gui object.
     */
    private void createAndShowGui() throws IOException {
        JFrame frame = new JFrame("JDig");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Sets the look and feel to the system default, then creates a Gui object.
     */
    @Override
    public void run() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            createAndShowGui();
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException |
                IOException ex) {
            // TO-DO: Stuff.
        }
    }

}
