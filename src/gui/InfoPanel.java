package gui;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers. Copyright (C) 2014 Fado@Epitaph.
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
import gui.infopanel.infocommands.SetDeterminate;
import gui.infopanel.infocommands.SetInclude;
import gui.infopanel.infocommands.SetInherit;
import gui.infopanel.infocommands.SetLight;
import gui.infopanel.infocommands.SetLong;
import gui.infopanel.infocommands.SetName;
import gui.infopanel.infocommands.SetShort;
import gui.streeteditor.StreetEditor;
import properties.Localization;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class InfoPanel extends JPanel {

    private final LabeledComponent roomNameField;
    private JComboBox streetNameField;
    private JButton addEditStreetsButton;
    private JLabel colorChooserButton;
    private final LabeledComponent includeField;
    private final LabeledComponent inheritField;
    private final LabeledComponent shortDescriptionField;
    private final LabeledComponent determinateField;
    private final LabeledComponent lightField;
    private final JPanel contentPanel;
    private final JPanel exitPanel;
    private final LabeledComponent longDescriptionField;
    private Room currentRoom;
    private List<Room> currentRooms = new ArrayList<>();
    private final Level level;
    private Color color;
    private final Localization localization = new Localization();
    private final String PROPAGATE_COLOR_MESS = localization.get("PropagateColorMessage");
    private CellPanel currentCellPanel;

    public InfoPanel(Level level) {
        this.level = level;
        setLayout(new MigLayout());
        contentPanel = createContentPanel();

        roomNameField = LabeledComponent.textField(localization.get("RoomNameLabel"),
                new InfoPanelDocListener(this, new SetName()));
        roomNameField.addtoPanel(contentPanel);

        includeField = LabeledComponent.textField(localization.get("IncludeLabel"),
                new InfoPanelDocListener(this, new SetInclude()));
        includeField.addtoPanel(contentPanel);

        inheritField = LabeledComponent.textField(localization.get("InheritLabel"),
                new InfoPanelDocListener(this, new SetInherit()));
        inheritField.addtoPanel(contentPanel);

        buildStreetNameField();

        buildAddEditStreetsButton();

        buildColorSelector();

        shortDescriptionField =
                LabeledComponent.textField(localization.get("ShortDescLabel"),
                new InfoPanelDocListener(this, new SetShort()));
        contentPanel.add(shortDescriptionField.getLabel());
        contentPanel.add(shortDescriptionField.getComponent(), "span, grow, wrap");

        determinateField =
                LabeledComponent.textField(localization.get("DeterminateLabel"),
                new InfoPanelDocListener(this, new SetDeterminate()));
        determinateField.addtoPanel(contentPanel);

        lightField = LabeledComponent.textField(localization.get("LightLabel"),
                new InfoPanelDocListener(this, new SetLight()));
        lightField.addtoPanel(contentPanel);

        longDescriptionField =
                LabeledComponent.textArea(localization.get("LongDescLabel"),
                new InfoPanelDocListener(this, new SetLong()));
        contentPanel.add(longDescriptionField.getLabel(), "wrap");
        contentPanel.add(longDescriptionField.getComponent(), "span, grow");

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
                if(!currentRooms.isEmpty()) {
                    for(Room room : currentRooms) {
                        room.setStreet((String) streetNameField.getSelectedItem());
                        Street street = level.getStreet(room.getStreet());
                        if(street != null) {
                            street.addRoom(room);
                        }
                    }
                }
                if (currentRoom != null) {
                    currentRoom.setStreet((String) streetNameField.getSelectedItem());
                    Street street = level.getStreet(currentRoom.getStreet());
                    if(street != null) {
                        street.addRoom(currentRoom);
                    }
                }
            }
        });
        populateStreetNames();
        JLabel streetNameLabel = new JLabel(localization.get("StreetName"), JLabel.RIGHT);
        streetNameLabel.setLabelFor(streetNameField);
        contentPanel.add(streetNameLabel);
        contentPanel.add(streetNameField);
    }

    private void buildAddEditStreetsButton() {
        addEditStreetsButton = new JButton(localization.get("EditStreetsLabel"));
        addEditStreetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                StreetEditor editor = new StreetEditor(InfoPanel.this);
                editor.run();
            }
        });
        contentPanel.add(addEditStreetsButton);
    }

    private void buildColorSelector() {
        colorChooserButton = new JLabel();
        colorChooserButton.setPreferredSize(new Dimension(16, 16));
        colorChooserButton.setVerticalAlignment(JLabel.CENTER);
        colorChooserButton.setBackground(Color.WHITE);
        colorChooserButton.setOpaque(true);
        colorChooserButton.setBorder(BorderFactory.createLineBorder(Color.black));
        colorChooserButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                color = JColorChooser.showDialog(null, "Choose", Color.WHITE);
                colorChooserButton.setBackground(color);
                if (!currentRooms.isEmpty()) {
                    for (Room room : currentRooms) {
                        colorRoom(room, room.getCellPanel());
                    }
                } else if (currentCellPanel != null) {
                    colorRoom(currentRoom, currentCellPanel);
                }
            }
        });
        contentPanel.add(colorChooserButton, "wrap");
    }

    private void colorRoom(Room room, CellPanel cellPanel) {
        cellPanel.setBackground(color);
        cellPanel.getCell().setColor(color);
        if (room.getStreet() != null) {
            colorStreet();
        }
    }

    private void colorStreet() {
        int value = JOptionPane.showOptionDialog(null, PROPAGATE_COLOR_MESS,
                localization.get("SelectOptionTitle"),
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (value == JOptionPane.YES_OPTION) {
            Street street = level.getStreet(currentRoom.getStreet());
            for (Room room : street.getRooms()) {
                room.getCellPanel().setBackground(color);
            }
        }
    }

    private JPanel createContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createTitledBorder(
                localization.get("AttributesTitle")));
        return panel;
    }

    private JPanel createExitPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setPreferredSize(new Dimension(400, 50));
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createTitledBorder(
                localization.get("ExitsTitle")));
        return panel;
    }

    public void updateExitPanel(Room room) {
        List<Exit> exits = room.getExits();
        exitPanel.removeAll();
        for (Exit exit : exits) {
            JLabel exitLabel = new JLabel(exit.getDirection().toString()
                    +", ", JLabel.RIGHT);
            exitPanel.add(exitLabel, "wrap");
        }
        exitPanel.validate();
        exitPanel.repaint();
    }

    public void populateStreetNames() {
        streetNameField.removeAllItems();
        streetNameField.addItem("");
        for (Street street : level.getStreets()) {
            streetNameField.addItem(street.getName());
        }
    }

    public Level getLevel() {
        return this.level;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }

    public void load(CellPanel cellPanel) {
        this.currentRoom = (Room)cellPanel.getCell().getEntity();
        this.currentRooms.add((Room)cellPanel.getCell().getEntity());
        this.currentCellPanel = cellPanel;
        this.roomNameField.setText(currentRoom.getName());
        this.includeField.setText(currentRoom.getInclude());
        this.inheritField.setText(currentRoom.getInherit());
        this.streetNameField.setSelectedItem(currentRoom.getStreet());
        this.colorChooserButton.setBackground(cellPanel.getBackground());
        this.determinateField.setText(currentRoom.getDeterminate());
        this.lightField.setText(currentRoom.getLight());
        this.shortDescriptionField.setText(currentRoom.getShort());
        this.longDescriptionField.setText(currentRoom.getLong());
        updateExitPanel(currentRoom);
    }

    public void loadMultiple(CellPanel cellPanel) {
        this.currentRooms.add((Room)cellPanel.getCell().getEntity());
    }

    public void unloadMultiple() {
        this.currentRooms.clear();
    }

}
