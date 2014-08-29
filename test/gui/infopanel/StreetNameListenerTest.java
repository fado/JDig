package gui.infopanel;

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
import data.Room;
import data.Street;
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

public class StreetNameListenerTest {

    Level level;
    List<Room> currentRooms = new ArrayList<>();
    InfoPanel infoPanel;
    ActionEvent testEvent;
    StreetNameListener streetNameListener;
    TestingUtils testingUtils = new TestingUtils();
    Room room, room2;
    JComboBox streetNameField;

    @Before
    public void setUp() {
        level = new Level(1, 1);
        infoPanel = new InfoPanel(level);
        room = new Room(null);
        room2 = new Room(null);
        currentRooms.add(room);
        currentRooms.add(room2);
        streetNameListener = new StreetNameListener(currentRooms, level);
        streetNameField = testingUtils.getStreetNameField(infoPanel);
        testEvent = new ActionEvent(streetNameField, ActionEvent.ACTION_PERFORMED, null);
    }

    @Test
    public void testStreetNameSet() {
        //noinspection unchecked
        streetNameField.addItem("foo");
        streetNameField.setSelectedItem("foo");
        streetNameListener.actionPerformed(testEvent);
        assertTrue(room.getStreetName().equals("foo"));
    }

    @Test
    public void testRoomAddedToStreet() {
        level.addStreet(new Street("foo"));
        //noinspection unchecked
        streetNameField.addItem("foo");
        streetNameField.setSelectedItem("foo");
        streetNameListener.actionPerformed(testEvent);
        assertTrue(level.getStreet("foo").size() == 2);
    }

}
