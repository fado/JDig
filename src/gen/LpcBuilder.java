package gen;

import data.Exit;
import data.Room;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

public class LpcBuilder {
 
    private final int TAB_LENGTH = 4;
    PrintWriter writer;
    
    public LpcBuilder(Room room) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println(writeExits(room));
    }

    private String writeExits(Room room) {
        String output = "";
        for (Exit exit : room.getExits()) {
            output += MessageFormat.format("add_exit (\"{0}\", __DIR__ +\"{1}\", \"{2}\");",
                    getExitName(exit),
                    exit.getDestination().getName(),
                    exit.getExitType().toString().toLowerCase()
            );
        }
        return output;
    }

    private String getTab() {
        StringBuilder builder = new StringBuilder();
        for(int counter = 0; counter < TAB_LENGTH; counter++) {
            builder.append(" ");
        }
        return builder.toString();
    }
    
    private String getExitName(Exit exit) {
        return exit.getDirection().toString().toLowerCase();
    }
    
}