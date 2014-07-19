package gen;

import data.Room;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.List;

public class LpcWriter {
    
    private final String TEMPLATE_PATH = "./resources/templates/room.template";
    private String output;
    private Writer writer;
    
    public void write(Room room) throws IOException {
        Template template = new Template();
        template.load(TEMPLATE_PATH);
        List<String> lines = template.getLines();
        System.out.println("Getting lines...");

        for(String string : lines) {
            output += string + "\n";
        }
        output = output.replace("%short%", room.getShort());
        output = output.replace("%determinate%", room.getDeterminate());
        output = output.replace("%long%", room.getLong());
        output = output.replace("%light%", ""+room.getLight());

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