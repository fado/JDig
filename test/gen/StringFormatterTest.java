package gen;

import data.Direction;
import data.Exit;
import data.ExitType;
import data.Room;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Administrator on 27/08/2014.
 */
public class StringFormatterTest {

    Room room, room2;
    StringFormatter stringFormatter;
    Properties testProperties;
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
