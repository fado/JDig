package gui.infopanel.streeteditor;

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

import data.Level;
import gui.infopanel.InfoPanel;
import main.BindingService;
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;
import javax.swing.JButton;
import static org.junit.Assert.assertFalse;

public class StreetEditorTest {

    StreetEditor streetEditor;
    TestingUtils testingUtils;

    @Before
    public void setUp() {
        testingUtils = new TestingUtils();
        Level level = new Level();
        BindingService bindingService = new BindingService(level);
        bindingService = testingUtils.setupBindingService(bindingService, level);
        testingUtils.populateLevel(1, 1, level);
        InfoPanel infoPanel = new InfoPanel(bindingService);
        streetEditor = new StreetEditor(infoPanel, bindingService);
    }

    @Test
    public void testAddButtonNotEnabled() {
        streetEditor.run();
        JButton deleteButton = testingUtils.getDeleteButton(streetEditor);
        assertFalse(deleteButton.isEnabled());
    }
}
