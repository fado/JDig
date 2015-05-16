package data;

import gui.GridSquare;
import java.util.List;
import java.util.Map;

/**
 * Created by Fado on 12/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class ExitBuilder {

    private Level level;
    private Map<String, Entity> entities;

    public ExitBuilder(Level level) {
        this.level = level;
    }

    public void build(GridSquare gridSquare, Connection connection) {
        ConnectionType connectionType = connection.getConnectionType();
        entities = level.getAllEntitiesAdjacentTo(gridSquare);

        if (connectionType == ConnectionType.HORIZONTAL) {
            buildExit("westCell", "eastCell", Direction.EAST);
            buildExit("eastCell", "westCell", Direction.WEST);
        }
        if (connectionType == ConnectionType.VERTICAL) {
            buildExit("northCell", "southCell", Direction.SOUTH);
            buildExit("southCell", "northCell", Direction.NORTH);
        }
        if (connectionType == ConnectionType.BACKWARD_DIAGONAL) {
            buildBackwardDiagonal();
        }
        if (connectionType == ConnectionType.FORWARD_DIAGONAL) {
            buildForwardDiagonal();
        }
        if (connectionType == ConnectionType.X) {
            buildBackwardDiagonal();
            buildForwardDiagonal();
        }
    }

    private void buildForwardDiagonal() {
        buildExit("northeastCell", "southwestCell", Direction.SOUTHWEST);
        buildExit("southwestCell", "northeastCell", Direction.NORTHEAST);
    }

    private void buildBackwardDiagonal() {
        buildExit("northwestCell", "southeastCell", Direction.SOUTHEAST);
        buildExit("southeastCell", "northwestCell", Direction.NORTHWEST);
    }

    private void buildExit(String origin, String destination, Direction direction) {
        Room originRoom = (Room)entities.get(origin);
        List<Exit> exits = originRoom.getExits();
        for (Exit exit : exits) {
            if (exit.getDirection() == direction) {
                return;
            }
        }
        Room destinationRoom = (Room)entities.get(destination);
        originRoom.addExit(new Exit(direction, destinationRoom, Exit.ExitType.PATH));
    }

}
