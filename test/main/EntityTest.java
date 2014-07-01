package main;

import org.junit.Test;
import static org.junit.Assert.*;

public class EntityTest {
    
    private final String ROOM = "./resources/images/room.png";
    private final String HORIZONTAL_EXIT = "./resources/images/horizontal_exit.png";
    private final String VERTICAL_EXIT = "./resources/images/vertical_exit.png";
    private final String FORWARD_DIAGONAL_EXIT = "./resources/images/forward_diagonal_exit.png";
    private final String BACKWARD_DIAGONAL_EXIT = "./resources/images/backward_diagonal_exit.png";
    private final String X_EXIT = "./resources/images/x_exit.png";

    /**
     * Hits methods generated in the bytecode to show 100% coverage.
     */
    @Test
    public void superficialCodeCoverage() {
        Entity.valueOf(Entity.ROOM.toString());
    }
    
    @Test
    public void testGetPathOfRoom() {
        assertEquals(ROOM, Entity.ROOM.getPath());
    }
    
    @Test
    public void testGetPathOfHorizontalExit() {
        assertEquals(HORIZONTAL_EXIT, Entity.HORIZONTAL_EXIT.getPath());
    }
    
    @Test
    public void testGetPathOfVerticalExit() {
        assertEquals(VERTICAL_EXIT, Entity.VERTICAL_EXIT.getPath());
    }
    
    @Test
    public void testGetPathOfForwardDiagonalExit() {
        assertEquals(FORWARD_DIAGONAL_EXIT, Entity.FORWARD_DIAGONAL_EXIT.getPath());
    }
    
    @Test
    public void testPathOfBackwardDiagonalExit() {
        assertEquals(BACKWARD_DIAGONAL_EXIT, Entity.BACKWARD_DIAGONAL_EXIT.getPath());
    }
    
    @Test
    public void testPathOfXExit() {
        assertEquals(X_EXIT, Entity.X_EXIT.getPath());
    }
    
    @Test
    public void testPathOfNoEntityIsNull() {
        assertTrue(Entity.NO_ENTITY.getPath() == null);
    }
    
}
