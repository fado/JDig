package gui;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph 
 * developers.  Copyright (C) 2014 Fado@Epitaph.
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
import data.Level;
import data.Room;
import data.Street;
import gui.streets.StreetEditor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JPanel {
    
    private JTextField roomNameField;
    private JComboBox streetNameField;
    private JButton addEditStreetsButton;
    private final JPanel contentPanel;
    private final JPanel exitPanel;
    private Room currentRoom;
    private Level level;
    
    public InfoPanel(Level level) {
        this.level = level;
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
        
        roomNameField = new JTextField();
        Dimension dimension = roomNameField.getPreferredSize();
        roomNameField.setPreferredSize(new Dimension(150, dimension.height));
        roomNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                currentRoom.setName(roomNameField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent de) {
                currentRoom.setName(roomNameField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent de) {
                currentRoom.setName(roomNameField.getText());
            }
        });
        JLabel roomNameLabel = new JLabel("Room name:", JLabel.RIGHT);
        roomNameLabel.setLabelFor(roomNameField);
        panel.add(roomNameLabel);
        panel.add(roomNameField, "wrap");
        
        streetNameField = new JComboBox();
        dimension = streetNameField.getPreferredSize();
        streetNameField.setPreferredSize(new Dimension(150, dimension.height));
        JLabel streetNameLabel = new JLabel("Street name:", JLabel.RIGHT);
        streetNameLabel.setLabelFor(streetNameField);
        populateStreetNames();
        streetNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(currentRoom != null) {
                    currentRoom.setStreet((String)streetNameField.getSelectedItem());
                }
            }            
        });
        panel.add(streetNameLabel);
        panel.add(streetNameField);
        
        addEditStreetsButton = new JButton("Add/Edit Streets");
        addEditStreetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                StreetEditor editor = new StreetEditor(InfoPanel.this);
                editor.run();
            }
        });
        panel.add(addEditStreetsButton);
        
        return panel;
    }
    
    public Level getLevel() {
        return this.level;
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
    
    public void populateStreetNames() {
        streetNameField.removeAllItems();
        streetNameField.addItem("        ");
        for(Street street : level.getStreets()) {
            streetNameField.addItem(street.getName());
        }
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
        this.currentRoom = room;
        this.roomNameField.setText(room.getName());
        this.streetNameField.setSelectedItem(room.getStreet());
        updateExitPanel(room);
    }

}