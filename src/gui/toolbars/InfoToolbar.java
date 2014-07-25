package gui.toolbars;

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

import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import data.Level;
import data.Room;
import gen.LpcWriter;

/**
 * Created by Administrator on 19/07/2014.
 */
public class InfoToolbar extends JToolBar {

    private Level level;
    private boolean showNotEmptyMessage = false;
    private final String NOT_EMPTY_MESS = "All rooms must have names before "+
            "generating LPC.";

    public InfoToolbar(Level level) {
        this.level = level;
        setDefaultProperties();
        this.add(ToolbarButtonBuilder.build("Generate", getGenerationListener()));
    }

    private void setDefaultProperties() {
        this.setFloatable(false);
    }

    private ActionListener getGenerationListener() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                for(Room room : level.getRooms()) {
                    if(room.getName().isEmpty()) {
                        showNotEmptyMessage = true;
                    }
                }
                if(showNotEmptyMessage) {
                    JOptionPane.showMessageDialog(null, NOT_EMPTY_MESS, "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
                LpcWriter writer = new LpcWriter();
                for(Room room : level.getRooms()) {
                    try {
                        writer.write(room);
                    } catch (IOException e) {
                        //TO-DO: Something.
                    }
                }
            }
        };
        return listener;
    }

}