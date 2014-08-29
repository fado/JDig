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

import data.Direction;
import data.Exit;
import data.ExitType;
import data.Room;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import static junit.framework.TestCase.assertEquals;

public class StringFormatterTest {

    Room room, room2;
    StringFormatter stringFormatter;
    String path;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws IOException {
        room = new Room(null);
        room2 = new Room(null);
        room2.setName("foo");
        room.addExit(new Exit(Direction.SOUTH, room2, ExitType.PATH));
        stringFormatter = new StringFormatter();
        // Setup a temporary template file.
        path = folder.getRoot().getPath();
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path +"/test"), Charset.defaultCharset()));
        String testString = "foo";
        writer.write(testString);
        writer.close();
    }

    @Test
    public void testGetExitString() {
        String actual = stringFormatter.getExitString(room);
        String expected = "add_exit (\"south\", __DIR__ +\"foo.c\", \"path\");\n    ";
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTemplateString() throws IOException {
        String expected = "foo";
        String actual = stringFormatter.getTemplateString(path + "/test");
        assertEquals(expected, actual);
    }


}
