package gen;

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


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.List;

import data.Exit;
import data.Room;

public class LpcWriter {
    
    private final String TEMPLATE_PATH = "./resources/templates/room.template";
    private Writer writer;
    
    public void write(Room room) throws IOException {
        Template template = new Template();
        template.load(TEMPLATE_PATH);
        List<String> lines = template.getLines();
        System.out.println("Getting lines...");

        String output = "";

        for(String string : lines) {
            output += string + "\n";
        }
        output = output.replace("%short%", room.getShort());
        output = output.replace("%determinate%", room.getDeterminate());
        output = output.replace("%long%", room.getLong());
        output = output.replace("%light%", ""+room.getLight());

        String exits = "";
        for (Exit exit : room.getExits()) {
            exits += MessageFormat.format("add_exit (\"{0}\", __DIR__ +\"{1}\", \"{2}\");\n    ",
                    exit.getDirection().toString().toLowerCase(),
                    exit.getDestination().getName(),
                    exit.getExitType().toString().toLowerCase()
            );
        }

        output = output.replace("%exit%", exits);

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("./lpc/"+ room.getName() +".c"), "UTF-8"));
            writer.write(output);
            writer.flush();
            System.out.println("Writing LPC file...");
        } catch (IOException ex) {
            // TO-DO: Something.
            ex.printStackTrace();
        }
    }
    
}