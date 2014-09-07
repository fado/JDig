package gui.infopanel.colorchooser;

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

import data.Room;
import data.Street;
import gui.infopanel.InfoPanel;
import main.BindingService;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * MouseAdapter for the ColorChooser.
 */
public class ColorChooserListener extends MouseAdapter {

    private InfoPanel infoPanel;
    private List<Street> streets = new ArrayList<>();
    private ColorChooser colorChooser;
    private RoomColorSetter roomColorSetter;
    private StreetColorSetter streetColorSetter;
    private BindingService bindingService;

    /**
     * Constructor.
     * @param infoPanel the InfoPanel in which the color chooser is created.
     */
    public ColorChooserListener(InfoPanel infoPanel, ColorChooser colorChooser,
                                RoomColorSetter roomColorSetter,
                                StreetColorSetter streetColorSetter,
                                BindingService bindingService){
        this.infoPanel = infoPanel;
        this.colorChooser = colorChooser;
        this.roomColorSetter = roomColorSetter;
        this.streetColorSetter = streetColorSetter;
        this.bindingService = bindingService;
    }

    /**
     * Determines what happens when the user clicks the color chooser panel.
     * @param event The event originating the call.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        Color color = colorChooser.showDialog();
        // Set the colorChooserButton background to the chosen color.
        JLabel colorChooserButton = (JLabel)event.getSource();
        colorChooserButton.setBackground(color);
        // Change the room color.
        if (!infoPanel.getCurrentRooms().isEmpty()) {
            for (Room room : infoPanel.getCurrentRooms()) {
                roomColorSetter.colorRoom(room, color);
                getStreet(room);
            }
            // If there are streets, see if the user wants to color them.
            if(!streets.isEmpty()) {
                colorStreets(color);
            }
        }
    }

    /**
     * Gets the street from the passed-in Room and adds it to the list
     * of Streets maintained by this object.  Won't add duplicate
     * entries.
     * @param room The room you want to get the street from.
     */
    private void getStreet(Room room) {
        String streetName = room.getStreetName();
        Street street = null;
        if(streetName != null) {
            street = infoPanel.getLevel().getStreet(streetName);
        }
        if(street != null && !streets.contains(street)) {
            streets.add(street);
        }
    }

    /**
     * For each Street that has been selected, check if the user wants
     * to change the color for the entire street.
     * @param color The color the Streets should be set to.
     */
    private void colorStreets(Color color) {
        for(Street street : streets) {
            streetColorSetter.colorStreet(street, color);
        }
    }
}
