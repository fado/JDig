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

import data.Exit;
import data.Room;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JPanel {
    
    private JTextField roomNameField;
    private JComboBox streetNameField;
    private JButton addEditStreetsButton;
    private JPanel contentPanel;
    private JPanel exitPanel;
    
    public InfoPanel() {
        setLayout(new MigLayout());
        contentPanel = createContentPanel();
        this.add(contentPanel, "wrap");
        exitPanel = createExitPanel();
        this.add(exitPanel);
    }
    
    private JPanel createContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setPreferredSize(new Dimension(400,200));
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createTitledBorder("Attributes"));
        
        roomNameField = new JTextField(20);
        JLabel roomNameLabel = new JLabel("Room name:", JLabel.RIGHT);
        roomNameLabel.setLabelFor(roomNameField);
        
        streetNameField = new JComboBox();
        JLabel streetNameLabel = new JLabel("Street name:", JLabel.RIGHT);
        streetNameLabel.setLabelFor(streetNameField);
        
        addEditStreetsButton = new JButton("Add/Edit Streets");
        
        panel.add(roomNameLabel);
        panel.add(roomNameField, "wrap");
        panel.add(streetNameLabel);
        panel.add(streetNameField);
        panel.add(addEditStreetsButton);
        
        return panel;
    }
    
    private JPanel createExitPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setPreferredSize(new Dimension(400,200));
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createTitledBorder("Exits"));
        
        return panel;
    }    
    
    public JPanel getContentPanel() {
        return this.contentPanel;
    }
    
    public JPanel getExitPanel() {
        return this.contentPanel;
    }
    
    public void updateExitPanel(Room room) {
        List<Exit> exits = room.getExits();
        exitPanel.removeAll();
        for(Exit exit : exits) {
            JLabel exitLabel = new JLabel(exit.getDirection().toString(), JLabel.RIGHT);
            exitPanel.add(exitLabel, "wrap");
        }
        exitPanel.validate();
        exitPanel.repaint();
    }
    
    public void load(Room room) {
        updateExitPanel(room);
    }

}