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
import gui.levelpanel.CellPanel;
import main.BindingService;
import java.awt.Color;

/**
 * Sets the color of a room.
 */
public class RoomColorSetter {

    private BindingService bindingService;

    public RoomColorSetter(BindingService bindingService) {
        this.bindingService = bindingService;
    }

    /**
     * Sets the passed in Room/CellPanel to the selected color.
     * @param room The Room whose color you want to change.
     * @param color The color to be set.
     */
    public void colorRoom(Room room, Color color) {
        CellPanel cellPanel = bindingService.getBoundCellPanel(room.getCell());
        cellPanel.setBackground(color);
        room.getCell().setColor(color);
    }

}
