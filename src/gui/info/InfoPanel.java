package gui.info;

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
import gui.info.streets.StreetEditor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JPanel {
    
    private JTextField roomNameField;
    private JComboBox streetNameField;
    private JButton addEditStreetsButton;
    private JTextField shortDescriptionField;
    private JTextField determinateField;
    private JTextField lightField;
    private final JPanel contentPanel;
    private final JPanel exitPanel;
    private JTextArea longDescriptionField;
    private Room currentRoom;
    private final Level level;
    
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
        panel.setPreferredSize(new Dimension(400,250));
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createTitledBorder("Attributes"));
        
        createRoomNameField(panel);
        createStreetNameField(panel);
        createAddEditStreetsButton(panel);
        createShortDescriptionField(panel);
        createDeterminateField(panel);
        createLightField(panel);
        createLongDescriptionField(panel);
        
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
    
    public void createRoomNameField(JPanel panel) {
        roomNameField = new JTextField();
        Dimension dimension = roomNameField.getPreferredSize();
        roomNameField.setPreferredSize(new Dimension(200, dimension.height));
        roomNameField.getDocument().addDocumentListener(
                new InfoPanelDocListener(this, new SetNameCommand()));
        JLabel roomNameLabel = new JLabel("Room name:", JLabel.RIGHT);
        roomNameLabel.setLabelFor(roomNameField);
        panel.add(roomNameLabel);
        panel.add(roomNameField, "wrap");
    }
    
    private void createStreetNameField(JPanel panel) {
        streetNameField = new JComboBox();
        Dimension dimension = streetNameField.getPreferredSize();
        streetNameField.setPreferredSize(new Dimension(200, dimension.height));
        streetNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(currentRoom != null) {
                    currentRoom.setStreet((String)streetNameField.getSelectedItem());
                }
            }            
        });
        populateStreetNames();
        JLabel streetNameLabel = new JLabel("Street name:", JLabel.RIGHT);
        streetNameLabel.setLabelFor(streetNameField);
        panel.add(streetNameLabel);
        panel.add(streetNameField);
        
    }
    
    private void createAddEditStreetsButton(JPanel panel) {
        addEditStreetsButton = new JButton("Add/Edit Streets");
        addEditStreetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                StreetEditor editor = new StreetEditor(InfoPanel.this);
                editor.run();
            }
        });
        panel.add(addEditStreetsButton, "wrap");
    }
    
    private void createShortDescriptionField(JPanel panel) {
        shortDescriptionField = new JTextField();
        Dimension dimension = shortDescriptionField.getPreferredSize();
        shortDescriptionField.setPreferredSize(new Dimension(200, dimension.height));
        shortDescriptionField.getDocument().addDocumentListener(
                new InfoPanelDocListener(this, new SetShortCommand()));
        JLabel shortDescriptionLabel = new JLabel("Short description:", JLabel.RIGHT);
        shortDescriptionLabel.setLabelFor(shortDescriptionField);
        panel.add(shortDescriptionLabel);
        panel.add(shortDescriptionField, "span, grow, wrap");
    }
    
    public void createDeterminateField(JPanel panel) {
        determinateField = new JTextField();
        Dimension dimension = determinateField.getPreferredSize();
        determinateField.setPreferredSize(new Dimension(200, dimension.height));
        determinateField.getDocument().addDocumentListener(
                new InfoPanelDocListener(this, new SetDeterminateCommand()));
        JLabel determinateLabel = new JLabel("Determinate:", JLabel.RIGHT);
        determinateLabel.setLabelFor(determinateField);
        panel.add(determinateLabel);
        panel.add(determinateField, "wrap");
    }
    
    public void createLightField(JPanel panel) {
        lightField = new JTextField();
        Dimension dimension = lightField.getPreferredSize();
        lightField.setPreferredSize(new Dimension(200, dimension.height));
        lightField.getDocument().addDocumentListener(
                new InfoPanelDocListener(this, new SetLightCommand()));
        JLabel lightFieldLabel = new JLabel("Light:", JLabel.RIGHT);
        lightFieldLabel.setLabelFor(lightField);
        panel.add(lightFieldLabel);
        panel.add(lightField, "wrap");
    }
    
    public void createLongDescriptionField(JPanel panel) {
        longDescriptionField = new JTextArea(5, 60);
        longDescriptionField.getDocument().addDocumentListener(
                new InfoPanelDocListener(this, new SetLongCommand()));
        JLabel longDescriptionLabel = new JLabel("Long description: ", JLabel.RIGHT);
        longDescriptionLabel.setLabelFor(longDescriptionField);
        panel.add(longDescriptionLabel, "wrap");
        panel.add(longDescriptionField, "span");
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
    
    public Level getLevel() {
        return this.level;
    }
    
    public Room getCurrentRoom() {
        return this.currentRoom;
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
    
    public void load(Room room) {
        this.currentRoom = room;
        this.roomNameField.setText(room.getName());
        this.streetNameField.setSelectedItem(room.getStreet());
        this.shortDescriptionField.setText(room.getShort());
        this.longDescriptionField.setText(room.getLong());
        updateExitPanel(room);
    }

}