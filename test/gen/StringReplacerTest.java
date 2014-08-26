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

import data.Room;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

public class StringReplacerTest {

    private String exitString, inputString, roomString;
    private StringReplacer stringReplacer;
    private Room room;

    @Before
    public void setUp() {
        exitString = "foo";
        inputString = "%exit%";
        stringReplacer = new StringReplacer();
        roomString = "%include%,%inherit%,%short%,%long%,%determinate%," +
                "%long%,%light%";
        room = new Room(null);
        room.setInclude("foo");
        room.setInherit("foo");
        room.setShort("foo");
        room.setDeterminate("foo");
        room.setLong("foo");
        room.setLight("foo");
    }

    @Test
    public void testReplaceExitStrings() {
        String actual = stringReplacer.replaceExitStrings(inputString, exitString);
        String expected = exitString;
        assertEquals(expected, actual);
    }

    @Test
    public void testReplaceRoomStrings() {
        String actual = stringReplacer.replaceRoomStrings(roomString, room);
        String[] actualSplit = actual.split(",");
        Boolean mismatch = false;
        for (String string : actualSplit) {
            if(!string.equalsIgnoreCase("foo")) {
                mismatch = true;
            }
        }
        assertFalse(mismatch);
    }
}
