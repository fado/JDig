package tools;

import data.Cell;
import data.Level;
import org.junit.Before;
import org.junit.Test;
import java.awt.Point;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PlacementRestrictionTest {

    Level testLevel;
    Cell oddCell, evenCell;
    PlacementRestriction placementRestriction;

    @Before
    public void setUp() {
        placementRestriction = new PlacementRestriction();
        testLevel = new Level(1, 1);

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
