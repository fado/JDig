package gui.toolbars;

import data.Level;
import data.Room;
import gen.LpcWriter;

import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Administrator on 19/07/2014.
 */
public class InfoToolbar extends JToolBar {

    private Level level;

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
