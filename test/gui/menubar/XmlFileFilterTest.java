package gui.menubar;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Administrator on 01/09/2014.
 */
public class XmlFileFilterTest {

    File xmlFile, fooFile, dirFile;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    XmlFileFilter xmlFileFilter;

    @Before
    public void setUp() throws IOException {
        xmlFile = File.createTempFile("foo", ".xml");
        fooFile = File.createTempFile("foo", ".foo");
        dirFile = mock(File.class);
        when(dirFile.isDirectory()).thenReturn(true);
        xmlFileFilter = new XmlFileFilter();
    }

    @Test
    public void testFilterReturnsTrueForXml() {
        assertTrue(xmlFileFilter.accept(xmlFile));
    }

    @Test
    public void testFilterReturnsFalseForFoo() {
        assertFalse(xmlFileFilter.accept(fooFile));
    }

    @Test
    public void testFilterReturnsTrueForDirectory() {
        assertTrue(xmlFileFilter.accept(dirFile));
    }

    @Test
    public void testGetDescription() {
        String expected = "Extensible Markup Language (*.XML)";
        String actual = xmlFileFilter.getDescription();
        assertTrue(expected.equalsIgnoreCase(actual));
    }
}
