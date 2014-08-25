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
import gui.CellPanel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import properties.Images;
import java.awt.Point;
import java.awt.event.MouseEvent;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RoomToolTest {

    Cell emptyCell, emptyCellSpy, oddCell, evenCell;
    CellPanel emptyCellPanel, emptyCellPanelSpy, oddCellPanel, oddCellPanelSpy,
        evenCellPanel, evenCellPanelSpy;
    MouseEvent eventEntered, eventEnteredEven, eventEnteredOdd, eventExited, eventRightPressed,
        eventLeftPressed;
    RoomTool roomTool;
    CellTool cellToolSpy;
    Images images;
    PlacementRestriction placementRestriction, placementRestrictionSpy;
    DeletionTool deletionToolSpy;
    Level testLevel, testLevelSpy;

    @Before
    public void setUp() {
        // Empty Cell and CellPanel.
        testLevel = new Level(1, 1);
        testLevelSpy = Mockito.spy(testLevel);
        Point testPoint = new Point(0, 0);
        emptyCell = new Cell(testPoint, testLevel);
        emptyCellSpy = Mockito.spy(emptyCell);
        emptyCellPanel = new CellPanel(emptyCell);
        emptyCellPanelSpy = Mockito.spy(emptyCellPanel);

        // MouseEvent - Enter emptyCellPanel.
        eventEntered = new MouseEvent(emptyCellPanel, MouseEvent.MOUSE_ENTERED,
                0, 0, 0, 0, 1, false);

        // RoomTool.
        CellTool cellTool = new CellTool();
        cellToolSpy = Mockito.spy(cellTool);
        DeletionTool deletionTool = new DeletionTool(cellTool);
        deletionToolSpy = Mockito.spy(deletionTool);
        placementRestriction = new PlacementRestriction();
        placementRestrictionSpy = Mockito.spy(placementRestriction);
        roomTool = new RoomTool(testLevelSpy, cellToolSpy, deletionToolSpy, placementRestrictionSpy);

        // Images.
        images = new Images();

        // Cell, odd parity.
        Point oddPoint = new Point(1, 1);
        oddCell = new Cell(oddPoint, testLevel);
        oddCellPanel = new CellPanel(oddCell);
        oddCellPanelSpy = Mockito.spy(oddCellPanel);

        // Cell, even parity.
        Point evenPoint = new Point(2, 2);
        evenCell = new Cell(evenPoint, testLevelSpy);
        evenCellPanel = new CellPanel(evenCell);
        evenCellPanelSpy = Mockito.spy(evenCellPanel);

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
        verify(cellToolSpy).addImage(emptyCellPanel, images.getImagePath("Room"));
    }

    /**
     * Test mouse entered with no Entity and valid position.
     */
    @Test
    public void testMouseEnteredValidPosition() {
        placementRestriction.setPlacementRestriction(evenCell);
        roomTool.mouseEntered(eventEnteredEven);
        verify(cellToolSpy).addImage(evenCellPanel, images.getImagePath("Room"));
    }

    /**
     * Test mouse entered with no Entity and invalid position.
     */
    @Test
    public void testMouseEnteredInvalidPosition() {
        placementRestrictionSpy.setPlacementRestriction(evenCell);
        roomTool.mouseEntered(eventEnteredOdd);
        verify(cellToolSpy).addImage(oddCellPanel, images.getImagePath("InvalidRoom"));
    }

    /**
     * Test Room not shown for Cell with Entity.
     */
    @Test
    public void testMouseEnteredEntityNotNullRoom() {
        emptyCellPanel.getCell().setEntity(new Room(null));
        roomTool.mouseEntered(eventEntered);
        verify(cellToolSpy, never()).addImage(emptyCellPanel, images.getImagePath("Room"));
    }

    /**
     * Test InvalidRoom not shown for Cell with Entity.
     */
    @Test
    public void testMouseEnteredEntityNotNullInvalidRoom() {
        emptyCellPanel.getCell().setEntity(new Room(null));
        roomTool.mouseEntered(eventEntered);
        verify(cellToolSpy, never()).addImage(emptyCellPanel, images.getImagePath("InvalidRoom"));
    }

    /**
     * Test mouse exited, no entity.
     */
    @Test
    public void testMouseExited() {
        roomTool.mouseExited(eventExited);
        verify(cellToolSpy).removeImage(emptyCellPanel);
    }

    /**
     * Test mouse exited, with entity.
     */
    @Test
    public void testMouseExitedWithEntity() {
        emptyCell.setEntity(new Room(null));
        roomTool.mouseExited(eventExited);
        verify(cellToolSpy, never()).removeImage(emptyCellPanel);
    }

    @Test
    public void testRightMousePressed() {
        roomTool.mousePressed(eventRightPressed);
        verify(deletionToolSpy).deleteEntity(emptyCellPanel);
    }

    @Test
    public void testRightPressedNoRooms() {
        roomTool.mousePressed(eventRightPressed);
        verify(placementRestrictionSpy).resetPlacementRestriction();
    }

    @Test
    public void testRightPressedWithRooms() {
        testLevel.registerRoom(new Room(null));
        roomTool.mousePressed(eventRightPressed);
        verify(placementRestrictionSpy, never()).resetPlacementRestriction();
    }

    @Test
    public void testLeftPressedValidPosition() {
        placementRestrictionSpy.setPlacementRestriction(evenCell);
        roomTool.mousePressed(eventLeftPressed);
        verify(testLevelSpy).registerRoom(any(Room.class));
    }

    @Test
    public void testLeftPressedInvalidPosition() {
        placementRestrictionSpy.setPlacementRestriction(oddCell);
        roomTool.mousePressed(eventLeftPressed);
        verify(testLevelSpy, never()).registerRoom(any(Room.class));
    }

    @Test
    public void testLeftPressedRestrictionSet() {
        placementRestrictionSpy.setPlacementRestriction(evenCell);
        roomTool.mousePressed(eventLeftPressed);
        verify(placementRestrictionSpy).setPlacementRestriction(any(Cell.class));
    }

}
