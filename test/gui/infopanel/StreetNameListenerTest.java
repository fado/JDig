package gui.infopanel;

import data.Level;
import data.Room;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 29/08/2014.
 */
public class StreetNameListenerTest {

    List<Room> currentRooms = new ArrayList<>();
    ActionEvent testEvent;
    StreetNameListener streetNameListener;

    @Before
    public void setUp() {
        Level level = new Level(1, 1);
        InfoPanel infoPanel = new InfoPanel(level);
        Room room = new Room(null);
        Room room2 = new Room(null);
        currentRooms.add(room);
        currentRooms.add(room2);

        streetNameListener = new StreetNameListener(currentRooms, level);

        testEvent = new ActionEvent(infoPanel, ActionEvent.ACTION_PERFORMED, null);
    }

    @Test
    public void testStreetNameSet() {

    }

}
