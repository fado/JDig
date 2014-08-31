package gui.infopanel;

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
import gui.JdigComponent;
import gui.infopanel.colorchooser.ColorChooserListener;
import gui.infopanel.colorchooser.ColorStreetDialog;
import gui.infopanel.colorchooser.DefaultColorChooser;
import gui.infopanel.colorchooser.RoomColorSetter;
import gui.infopanel.colorchooser.StreetColorSetter;
import gui.levelpanel.CellPanel;
import gui.infopanel.infosetters.*;
import gui.infopanel.streeteditor.StreetEditor;
import properties.Localization;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 * InfoPanel is responsible for displaying all information contained within the
 * currently selected CellPanel, and allowing the user to manipulate the data
 * contained within the corresponding Cell.
 */
public class InfoPanel extends JPanel implements JdigComponent {

    private LabeledComponent roomNameField;
    private JComboBox<String> streetNameField;
    private JLabel colorChooserButton;
    private LabeledComponent includeField;
    private LabeledComponent inheritField;
    private LabeledComponent shortDescriptionField;
    private LabeledComponent determinateField;
    private LabeledComponent lightField;
    private final JPanel exitPanel;
    private LabeledComponent longDescriptionField;
    private List<Room> currentRooms = new ArrayList<>();
    private final Level level;
    private final Localization localization = new Localization();

    public InfoPanel(Level level) {
        this.level = level;
        setLayout(new MigLayout());
        JPanel contentPanel = createContentPanel();
        contentPanel.setName("contentPanel");
        this.add(contentPanel, "wrap");
        exitPanel = createExitPanel();
        this.add(exitPanel);
    }

    /**
     * Builds the StreetNameField and populates the ComboBox with the names of
     * the Streets within the Level.
     */
    private void buildStreetNameField(JPanel panel) {
        streetNameField = new JComboBox<>();
        streetNameField.setName("streetNameField");
        Dimension dimension = streetNameField.getPreferredSize();
        streetNameField.setPreferredSize(new Dimension(200, dimension.height));
        streetNameField.addActionListener(new StreetNameListener(currentRooms, level));
        populateStreetNames();
        JLabel streetNameLabel = new JLabel(localization.get("StreetName"), JLabel.RIGHT);
        streetNameLabel.setLabelFor(streetNameField);
        panel.add(streetNameLabel);
        panel.add(streetNameField);
    }

    /**
     * Builds the button that starts the StreetEditor.
     */
    private void buildAddEditStreetsButton(JPanel panel) {
        JButton addEditStreetsButton = new JButton(localization.get("EditStreetsLabel"));
        addEditStreetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                StreetEditor editor = new StreetEditor(InfoPanel.this);
                editor.run();
            }
        });
        panel.add(addEditStreetsButton);
    }

    /**
     * Builds a ColorSelector which allows the user to change the color
     * of one or more Rooms/CellPanels within the Level.
     */
    private void buildColorSelector(JPanel panel) {
        colorChooserButton = new JLabel();
        colorChooserButton.setPreferredSize(new Dimension(16, 16));
        colorChooserButton.setVerticalAlignment(JLabel.CENTER);
        colorChooserButton.setBackground(Color.WHITE);
        colorChooserButton.setOpaque(true);
        colorChooserButton.setBorder(BorderFactory.createLineBorder(Color.black));
        colorChooserButton.addMouseListener(new ColorChooserListener(this,
                new DefaultColorChooser(), new RoomColorSetter(),
                new StreetColorSetter(new ColorStreetDialog())));
        panel.add(colorChooserButton, "wrap");
    }

    /**
     * Creates the main content panel of the InfoPanel.
     * @return the main content panel of the InfoPanel
     */
    private JPanel createContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createTitledBorder(
                localization.get("AttributesTitle")));

        roomNameField = LabeledComponent
                .textField(localization.get("RoomNameLabel"),
                        new InfoPanelDocListener(this, new SetName()));
        roomNameField.addToPanel(panel);

        includeField = LabeledComponent
                .textField(localization.get("IncludeLabel"),
                        new InfoPanelDocListener(this, new SetInclude()));
        includeField.addToPanel(panel);

        inheritField = LabeledComponent
                .textField(localization.get("InheritLabel"),
                        new InfoPanelDocListener(this, new SetInherit()));
        inheritField.addToPanel(panel);

        buildStreetNameField(panel);

        buildAddEditStreetsButton(panel);

        buildColorSelector(panel);

        shortDescriptionField = LabeledComponent
                .textField(localization.get("ShortDescLabel"),
                        new InfoPanelDocListener(this, new SetShort()));
        panel.add(shortDescriptionField.getLabel());
        panel.add(shortDescriptionField.getComponent(), "span, grow, wrap");

        determinateField = LabeledComponent
                .textField(localization.get("DeterminateLabel"),
                        new InfoPanelDocListener(this, new SetDeterminate()));
        determinateField.addToPanel(panel);

        lightField = LabeledComponent
                .textField(localization.get("LightLabel"),
                        new InfoPanelDocListener(this, new SetLight()));
        lightField.addToPanel(panel);

        longDescriptionField = LabeledComponent
                .textArea(localization.get("LongDescLabel"),
                        new InfoPanelDocListener(this, new SetLong()));
        panel.add(longDescriptionField.getLabel(), "wrap");
        panel.add(longDescriptionField.getComponent(), "span, grow");

        return panel;
    }

    /**
     * Creates the ExitPanel.  It displays whatever Exits are in the Room
     * currently loaded in the InfoPanel.
     * @return the ExitPanel
     */
    private JPanel createExitPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout());
        panel.setPreferredSize(new Dimension(400, 50));
        panel.setOpaque(true);
        panel.setBorder(BorderFactory.createTitledBorder(
                localization.get("ExitsTitle")));
        return panel;
    }

    /**
     * Updates the ExitPanel with the Exits in the passed-in Room.
     * @param room The Room for which you want to loadRoom the Exits.
     */
    public void updateExitPanel(Room room) {
        exitPanel.removeAll();
        List<Exit> exits = room.getExits();
        for (Exit exit : exits) {
            JLabel exitLabel = new JLabel(exit.getDirection().toString()
                    +", ", JLabel.RIGHT);
            exitPanel.add(exitLabel, "wrap");
        }
        exitPanel.validate();
        exitPanel.repaint();
    }

    /**
     * Populates the street name field.  Removes all items already in the field,
     * adds a blank item, then adds a new item for each Street in the Level.
     */
    public void populateStreetNames() {
        streetNameField.removeAllItems();
        streetNameField.addItem("");
        for (Street street : level.getStreets()) {
            streetNameField.addItem(street.getName());
        }
    }

    /**
     * Get the GridBagConstraints for this object.
     */
    public GridBagConstraints getConstraints() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 1;
        return constraints;
    }

    /**
     * Returns the Level currently loaded in the application.
     * @return the Level currently loaded in the application.
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Gets the Room at the top of the currentRooms list, i.e. the Room at the
     * first index in the array.
     * @return the Room at the first index in the currentRooms array, or null if
     * no such room exists.
     */
    public Room getCurrentRoom() {
        if(!currentRooms.isEmpty()) {
            return currentRooms.get(0);
        }
        return null;
    }

    /**
     * Returns all currently loaded rooms.
     * @return all currently loaded rooms.
     */
    public List<Room> getCurrentRooms() {
        return currentRooms;
    }

    /**
     * Checks the passed-in CellPanel for a connectible Entity.  If the Entity
     * is connectible, this method takes the information it needs from the CellPanel
     * before passing the Room on to be loaded into the InfoPanel.
     * @param cellPanel The CellPanel from which you wish to loadRoom the data.
     */
    public void load(CellPanel cellPanel) {
        // Double-check we're dealing with a Room.
        if(cellPanel.getCell().isConnectible()) {
            // Get the data we need from the CellPanel.
            this.colorChooserButton.setBackground(cellPanel.getBackground());
            // Load the Room.
            Room room = (Room)cellPanel.getCell().getEntity();
            loadRoom(room);
        }
    }

    /**
     * Loads data from the passed-in Room into the InfoPanel.
     * @param room The Room you wish to loadRoom into the InfoPanel.
     */
    private void loadRoom(Room room) {
        if(currentRooms.isEmpty()) {
            // Add the passed-in Room to the list of selected Rooms.
            this.currentRooms.add(room);
            // Load the data from the Room into the InfoPanel.
            this.roomNameField.setText(room.getName());
            this.includeField.setText(room.getInclude());
            this.inheritField.setText(room.getInherit());
            this.streetNameField.setSelectedItem(room.getStreetName());
            this.determinateField.setText(room.getDeterminate());
            this.lightField.setText(room.getLight());
            this.shortDescriptionField.setText(room.getShort());
            this.longDescriptionField.setText(room.getLong());
            updateExitPanel(room);
        } else {
            this.currentRooms.add(room);
        }
    }

    /**
     * Unloads Rooms by clearing the currentRooms List.
     */
    public void unload() {
        this.currentRooms.clear();
    }

}
