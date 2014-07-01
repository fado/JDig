package main;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.mockito.Mockito;

public class JdigTest {
    
    @Test
    public void testJDigMain() {
        Jdig jdig = new Jdig();
        assertNotNull(jdig);
    }
    
    @Test
    public void testJdigMainInvokesMapLoader() {
        MapLoader loader = Mockito.mock(MapLoader.class);
        Jdig.setMapLoader(loader);
        Jdig.main(new String[] { });
        Mockito.verify(loader).newMap();
    }
    
}
