package gui.menubar;

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
