package gui.menubar;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener for menu items.
 */
public class MenuActionListener implements ActionListener {

    private final Command command;

    /**
     * Constructor.
     * @param command The Command to be executed by the MenuItem.
     */
    public MenuActionListener(Command command) {
        this.command = command;
    }

    /**
     * Executes the command.
     * @param event The event originating the call.
     */
    @Override
    public void actionPerformed(ActionEvent event) {
            command.execute();
    }

}
