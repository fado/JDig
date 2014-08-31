package gui.menubar;

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

import data.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.JOptionPane;

public class ExitCommand extends Command {

    private final Level level;
    private LoadDialog loadDialog;
    private Command saveCommand;
    static final Logger logger = LoggerFactory.getLogger(ExitCommand.class);

    public ExitCommand(Level level, LoadDialog loadDialog, SaveCommand saveCommand) {
        this.level = level;
        this.loadDialog = loadDialog;
        this.saveCommand = saveCommand;
    }

    public void execute() {
        if(!level.getRooms().isEmpty()) {
            int option = loadDialog.showDialog();
            if(option == JOptionPane.YES_OPTION) {
                saveCommand.execute();
            }
            if(option == JOptionPane.NO_OPTION) {
                logger.info("Application shutting down.");
                System.exit(0);
            }
        } else {
            logger.info("Application shutting down.");
            System.exit(0);
        }
    }

}
