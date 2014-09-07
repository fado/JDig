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
import gui.levelpanel.CellPanel;
import main.BindingService;
import org.junit.Before;
import org.junit.Test;
import properties.Images;
import utils.TestingUtils;

import java.awt.Point;
import java.awt.event.MouseEvent;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class RoomToolTest {

    Cell emptyCell, oddCell, evenCell;
    CellPanel emptyCellPanel, oddCellPanel, evenCellPanel;
    MouseEvent eventEntered, eventEnteredEven, eventEnteredOdd, eventExited, eventRightPressed,
        eventLeftPressed;
    RoomTool roomTool;
    Images images;
    PlacementRestriction placementRestriction;
    Level testLevel;
    TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        // Empty Cell and CellPanel.
        testLevel = new Level();
        testLevel = testingUtils.populateLevel(3, 3, testLevel);
        BindingService bindingService = new BindingService(testLevel);
        bindingService = testingUtils.setupBindingService(bindingService, testLevel);

        Point testPoint = new Point(0, 0);
        emptyCell = testLevel.getCellAt(testPoint);
        emptyCellPanel = bindingService.getBoundCellPanel(emptyCell);

        // MouseEvent - Enter emptyCellPanel.
        eventEntered = new MouseEvent(emptyCellPanel, MouseEvent.MOUSE_ENTERED,
                0, 0, 0, 0, 1, false);

        // RoomTool.
        DeletionTool deletionTool = new DeletionTool(testLevel, bindingService);
        placementRestriction = new PlacementRestriction();
        roomTool = new RoomTool(testLevel, deletionTool, placementRestriction, bindingService,
                new DefaultOverwriteDialog());

        // Images.
        images = new Images();

        // Cell, odd parity.
        Point oddPoint = new Point(1, 1);
        oddCell = testLevel.getCellAt(oddPoint);
        oddCellPanel = bindingService.getBoundCellPanel(oddCell);

        // Cell, even parity.
        Point evenPoint = new Point(2, 2);
        evenCell = testLevel.getCellAt(evenPoint);
        evenCellPanel = bindingService.getBoundCellPanel(evenCell);

        // MouseEvent - Enter evenCellPanel.
        eventEnteredEven = new MouseEvent(evenCellPanel, MouseEvent.MOUSE_ENTERED,
                0, 0, 0, 0, 1, false);

        // MouseEvent - Enter oddCellPanel.
        eventEnteredOdd = new MouseEvent(oddCellPanel, MouseEvent.MOUSE_ENTERED,
                0, 0, 0, 0, 1, false);

        // MouseEvent - Exit CellPanel.
        eventExited = new MouseEvent(emptyCellPanel, MouseEvent.MOUSE_EXITED,
                0, 0, 0, 0, 1, false);

        // MouseEvent - Right pressed.
        eventRightPressed = new MouseEvent(emptyCellPanel, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false, MouseEvent.BUTTON3);

        // MouseEvent - Left pressed, even.
        eventLeftPressed = new MouseEvent(evenCellPanel, MouseEvent.MOUSE_PRESSED,
                0, 0, 0, 0, 1, false, MouseEvent.BUTTON1);
    }

    @Test
    public void testMouseEntered() {
        roomTool.mouseEntered(eventEntered);
        assertTrue(emptyCellPanel.hasEntityImage());
    }

    /**
     * Test mouse entered with no Entity and valid position.
     */
    @Test
    public void testMouseEnteredValidPosition() {
        placementRestriction.setPlacementRestriction(evenCell);
        roomTool.mouseEntered(eventEnteredEven);
        String expected = images.getImagePath("Room");
        String actual = evenCellPanel.getEntityImagePath();
        assertEquals(expected, actual);
    }

    /**
     * Test mouse entered with no Entity and invalid position.
     */
    @Test
    public void testMouseEnteredInvalidPosition() {
        placementRestriction.setPlacementRestriction(evenCell);
        roomTool.mouseEntered(eventEnteredOdd);
        String expected = images.getImagePath("InvalidRoom");
        String actual = oddCellPanel.getEntityImagePath();
        assertEquals(expected, actual);
    }

    /**
     * Test Room not shown for Cell with Entity.
     */
    @Test
    public void testMouseEnteredEntityNotNullRoom() {
        //emptyCellPanel.getCell().setEntity(new Room(null));
        emptyCell.setEntity(new Room(null));
        roomTool.mouseEntered(eventEntered);
        assertFalse(emptyCellPanel.hasEntityImage());
    }

    /**
     * Test mouse exited, no entity.
     */
    @Test
    public void testMouseExited() {
        roomTool.mouseExited(eventExited);
        assertFalse(emptyCellPanel.hasEntityImage());
    }

    /**
     * Test mouse exited, with entity.
     */
    @Test
    public void testMouseExitedWithEntity() {
        roomTool.mouseEntered(eventEntered);
        emptyCell.setEntity(new Room(null));
        roomTool.mouseExited(eventExited);
        assertTrue(emptyCellPanel.hasEntityImage());
    }

    @Test
    public void testRightMousePressed() {
        roomTool.mousePressed(eventRightPressed);
        assertFalse(emptyCellPanel.hasEntityImage());
    }

    @Test
    public void testRightPressedNoRooms() {
        roomTool.mousePressed(eventRightPressed);
        assertFalse(placementRestriction.isSet());
    }

    @Test
    public void testRightPressedWithRooms() {
        testLevel.registerRoom(new Room(null));
        placementRestriction.setPlacementRestriction(emptyCell);
        roomTool.mousePressed(eventRightPressed);
        assertTrue(placementRestriction.isSet());
    }

    @Test
    public void testLeftPressedValidPosition() {
        placementRestriction.setPlacementRestriction(evenCell);
        roomTool.mousePressed(eventLeftPressed);
        assertFalse(testLevel.getRooms().isEmpty());
    }

    @Test
    public void testLeftPressedInvalidPosition() {
        placementRestriction.setPlacementRestriction(oddCell);
        roomTool.mousePressed(eventLeftPressed);
        assertTrue(testLevel.getRooms().isEmpty());
    }

    @Test
    public void testLeftPressedRestrictionSet() {
        placementRestriction.setPlacementRestriction(evenCell);
        roomTool.mousePressed(eventLeftPressed);
        assertTrue(placementRestriction.isSet());
    }

}
