package gen;

/**
 * Created by Administrator on 19/07/2014.
 */

import data.Room;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestLpcWriter {

    LpcWriter lpcWriter;

    @Before
    public void setUp() {
        lpcWriter = new LpcWriter();
    }

    @Test
    public void testWrite() throws IOException {
        Room room = new Room();
        room.setShort("test short.");
        room.setDeterminate("the");
        room.setLong("Test long.");
        room.setLight("100");
        room.setName("test_room");
        lpcWriter.write(room);
    }

}
