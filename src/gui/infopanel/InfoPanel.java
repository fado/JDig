package gui.infopanel;

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

import gui.infopanel.commands.SetLong;
import gui.infopanel.commands.SetLight;
import gui.infopanel.commands.SetDeterminate;
import gui.infopanel.commands.SetName;
import gui.infopanel.commands.SetShort;
import data.Exit;
import data.Level;
import data.Room;
import data.Street;
import gui.infopanel.streeteditor.StreetEditor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JPanel {
    
    private final LabeledComponent roomNameField;
    private JComboBox streetNameField;
    private JButton addEditStreetsButton;
    private final LabeledComponent shortDescriptionField;
    private final LabeledComponent determinateField;
    private final LabeledComponent lightField;
    private final JPanel contentPanel;
    private final JPanel exitPanel;
    private final LabeledComponent longDescriptionField;
    private Room currentRoom;
    private final Level level;
    
    public InfoPanel(Level level) {
        this.level = level;
        setLayout(new MigLayout());
        contentPanel = createContentPanel();
        
        roomNameField = LabeledComponent.textField("Room name: ", 
                new InfoPanelDocListener(this, new SetName()));
        roomNameField.addtoPanel(contentPanel);
        
        buildStreetNameField();
        
        buildAddEditStreetsButton();
        
        shortDescriptionField = LabeledComponent.textField("Short description: ", 
                new InfoPanelDocListener(this, new SetShort()));
        shortDescriptionField.addtoPanel(contentPanel);
        
        determinateField = LabeledComponent.textField("Determinate: ", 
                new InfoPanelDocListener(this, new SetDeterminate()));
        determinateField.addtoPanel(contentPanel);
        
        lightField = LabeledComponent.textField("Light: ", 
                new InfoPanelDocListener(this, new SetLight()));
        lightField.addtoPanel(contentPanel);
        
        longDescriptionField = LabeledComponent.textField("Long description: ", 
                new InfoPanelDocListener(this, new SetLong()));
        longDescriptionField.addtoPanel(contentPanel);     
        
        this.add(contentPanel, "wrap");
        exitPanel = createExitPanel();
        this.add(exitPanel);
    }
    
    private void buildStreetNameField() {
        streetNameField = new JComboBox();
        Dimension dimension = streetNameField.getPreferredSize();
        streetNameField.setPreferredSize(new Dimension(200, dimension.height));
        streetNameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (currentRoom != null) {
                    currentRoom.setStreet((String) streetNameField.getSelectedItem());
                }
            }
        });
        populateStreetNames();
        JLabel streetNameLabel = new JLabel("Street name:", JLabel.RIGHT);
        streetNameLabel.setLabelFor(streetNameField);
        contentPanel.add(streetNameLabel);
        contentPanel.add(streetNameField);
    }
    
    private void buildAddEditStreetsButton() {
        addEditStreetsButton = new JButton("Add/Edit Streets");
        addEditStreetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                StreetEditor editor = new StreetEditor(InfoPanel.this);
                editor.run();
            }
        });
        contentPanel.add(addEditStreetsButton, "wrap");
    }
    
    private JPanel createContentPanel() {
        JPanel conentPanel = new JPanel();
        conentPanel.setLayout(new MigLayout());
        conentPanel.setPreferredSize(new Dimension(400,250));
        conentPanel.setOpaque(true);
        conentPanel.setBorder(BorderFactory.createTitledBorder("Attributes"));
        return conentPanel;
    }
    
    private JPanel createExitPanel() {
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new MigLayout());
        exitPanel.setPreferredSize(new Dimension(400,200));
        exitPanel.setOpaque(true);
        exitPanel.setBorder(BorderFactory.createTitledBorder("Exits"));
        return exitPanel;
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