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
import org.junit.Before;
import org.junit.Test;
import utils.TestingUtils;

import java.awt.Point;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlacementRestrictionTest {

    Level testLevel;
    Cell oddCell, evenCell;
    PlacementRestriction placementRestriction;
    TestingUtils testingUtils = new TestingUtils();

    @Before
    public void setUp() {
        placementRestriction = new PlacementRestriction();
        testLevel = new Level();
        testingUtils.populateLevel(1, 1, testLevel);
        // Cell, odd parity.
        Point oddPoint = new Point(1, 1);
        oddCell = new Cell(oddPoint, testLevel);

        // Cell, even parity.
        Point evenPoint = new Point(2, 2);
        evenCell = new Cell(evenPoint, testLevel);
    }

    @Test
    public void testOddParityWithOddCell() {
        placementRestriction.setPlacementRestriction(oddCell);
        assertTrue(placementRestriction.positionIsValid(oddCell));
    }

    @Test
    public void testOddParityWithEvenCell() {
        placementRestriction.setPlacementRestriction(oddCell);
        assertFalse(placementRestriction.positionIsValid(evenCell));
    }

    @Test
    public void testEvenParityYWithEvenCell() {
        placementRestriction.setPlacementRestriction(evenCell);
        assertTrue(placementRestriction.positionIsValid(evenCell));
    }

    @Test
    public void testEvenParityWithOddCell() {
        placementRestriction.setPlacementRestriction(evenCell);
        assertFalse(placementRestriction.positionIsValid(oddCell));
    }

}
