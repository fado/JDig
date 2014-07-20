package persistence;

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

import com.thoughtworks.xstream.XStream;
import data.Cell;
import data.Exit;
import data.Level;
import data.Room;
import data.Street;
import gui.MainUI;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class LevelPersistence {

    private Writer writer;
    private XStream xStream = new XStream();

    public void save(Level level) {
        String xml = xStream.toXML(level);

        xStream.alias("level", Level.class);
        xStream.alias("cell", Cell.class);
        xStream.alias("room", Room.class);
        xStream.alias("street", Street.class);
        xStream.alias("exit", Exit.class);

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./saves/"+ level +".xml"), "UTF-8"));
            writer.write(xml);
            writer.flush();
        } catch (IOException ex) {
            //TO-DO: Something.
        }
    }

    public void load(String path) throws IOException {
        Level level = (Level)xStream.fromXML(path);
        MainUI ui = new MainUI(level);
        ui.run();
    }
}
