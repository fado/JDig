package tools;

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

import data.Cell;
import data.Level;
import data.Room;
import gui.infopanel.InfoPanel;
import gui.levelpanel.CellPanel;
import main.BindingService;
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;

import java.awt.Point;
import java.awt.event.MouseEvent;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SelectionToolTest {

    private MouseEvent mousePressed, mousePressed2, mousePressed3;
    private SelectionTool selectionTool;
    private Cell testCell, testCell2, testCell3;
    private CellPanel testCellPanel, testCellPanel2, testCellPanel3;
    private TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        Level testLevel = new Level();
        testingUtils.populateLevel(3, 3, testLevel);

        BindingService bindingService = new BindingService(testLevel);
        bindingService = testingUtils.setupBindingService(bindingService, testLevel);

        //testCell = new Cell(new Point(0, 0), testLevel);
        testCell = testLevel.getCellAt(new Point(0, 0));
        //testCellPanel = new CellPanel(testCell);
        testCellPanel = bindingService.getBoundCellPanel(testCell);

        //testCell2 = new Cell(new Point(1, 1), testLevel);
        testCell2 = testLevel.getCellAt(new Point(1, 1));
        //testCellPanel2 = new CellPanel(testCell2);
        testCellPanel2 = bindingService.getBoundCellPanel(testCell2);

        //testCell3 = new Cell(new Point(2, 2), testLevel);
        testCell3 = testLevel.getCellAt(new Point(2, 2));
        //testCellPanel3 = new CellPanel(testCell3);
        testCellPanel3 = bindingService.getBoundCellPanel(testCell3);

        mousePressed = new MouseEvent(testCellPanel, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false);

        mousePressed2 = new MouseEvent(testCellPanel2, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false);

        mousePressed3 = new MouseEvent(testCellPanel3, MouseEvent.MOUSE_PRESSED,
                0, MouseEvent.SHIFT_DOWN_MASK, 0, 0, 1, false);

        InfoPanel infoPanel = new InfoPanel(testLevel, bindingService);
        selectionTool = new SelectionTool(infoPanel, bindingService);
    }

    @Test
    public void testMousePressedNotConnectible() {
        selectionTool.mousePressed(mousePressed);
        assertTrue(selectionTool.getSelectedPanels().isEmpty());
    }

    @Test
    public void testMousePressedConnectible() {
        testCell.setEntity(new Room(null));
        selectionTool.mousePressed(mousePressed);
        assertFalse(selectionTool.getSelectedPanels().isEmpty());
    }

    @Test
    public void testMousePressedConnectibleSelectsPanel() {
        testCell.setEntity(new Room(null));
        selectionTool.mousePressed(mousePressed);
        assertTrue(selectionTool.getSelectedPanels().get(0).isSelected());
    }

    @Test
    public void testMousePressedConnectibleDeselectsPrevious() {
        testCell.setEntity(new Room(null));
        testCell2.setEntity(new Room(null));
        selectionTool.mousePressed(mousePressed);
        selectionTool.mousePressed(mousePressed2);
        assertEquals(testCellPanel2, selectionTool.getSelectedPanels().get(0));
    }

    @Test
    public void testMousePressedDeselectsAll() {
        testCell.setEntity(new Room(null));
        selectionTool.mousePressed(mousePressed);
        selectionTool.mousePressed(mousePressed2);
        assertTrue(selectionTool.getSelectedPanels().isEmpty());
    }

    @Test
    public void testMousePressedSelectsMultipleWithShiftDown() {
        testCell.setEntity(new Room(null));
        testCell3.setEntity(new Room(null));
        selectionTool.mousePressed(mousePressed);
        selectionTool.mousePressed(mousePressed3);
        assertTrue(selectionTool.getSelectedPanels().size() == 2);
    }

}
