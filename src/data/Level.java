package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class Level {

    private final List<Entity> allEntities;
    Logger logger = LoggerFactory.getLogger(Level.class);

    public Level() {
        this.allEntities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        allEntities.add(entity);
        logger.debug("Adding cell at {},{}", entity.X, entity.Y);
        logger.debug("Cells in level: {}", allEntities.size());
    }

    public void removeEntityAt(Point point) {
        allEntities.remove(getEntityAt(point));
        logger.debug("Removing cell from {},{}", point.x, point.y);
        logger.debug("Cells in level: {}", allEntities.size());
    }

    public Entity getEntityAt(Point point) {
        for (Entity entity : allEntities) {
            if (entity.X == point.x && entity.Y == point.y) {
                return entity;
            }
        }
        return null;
    }

    public ConnectionType getPotentialConnectionTypeAt(Point point) {
        Map<String, Entity> entities = getAllEntitiesAdjacentTo(point);

        if (entities.get("westCell").isConnectible()
                && entities.get("eastCell").isConnectible()) {
            return ConnectionType.HORIZONTAL;
        }
        if (entities.get("northCell").isConnectible()
                && entities.get("southCell").isConnectible()) {
            return ConnectionType.VERTICAL;
        }
        if (entities.get("northwestCell").isConnectible()
                && entities.get("northeastCell").isConnectible()
                && entities.get("southwestCell").isConnectible()
                && entities.get("southeastCell").isConnectible()) {
            return ConnectionType.X;
        } else if (entities.get("southwestCell").isConnectible()
                && entities.get("northeastCell").isConnectible()) {
            return ConnectionType.FORWARD_DIAGONAL;
        } else if (entities.get("southeastCell").isConnectible()
                && entities.get("northwestCell").isConnectible()) {
            return ConnectionType.BACKWARD_DIAGONAL;
        }
        return ConnectionType.NONE;
    }

    public Map<String, Entity> getAllEntitiesAdjacentTo(Point point) {
        Map<String, Entity> cells = new HashMap<>();
        Direction[] directions = Direction.values();
        String[] gridNames = {
                "northCell",
                "southCell",
                "eastCell",
                "westCell",
                "northeastCell",
                "northwestCell",
                "southeastCell",
                "southwestCell"
        };

        for (int counter = 0; counter < gridNames.length; counter++) {
            cells.put(gridNames[counter], getEntityAdjacentTo(point, directions[counter]));
        }
        return cells;
    }

    public Entity getEntityAdjacentTo(Point referencePoint, Direction direction) {
        for (Entity entity : allEntities) {
            if (entity.X == direction.translateX(referencePoint.x) &&
                    entity.Y == direction.translateY(referencePoint.y)) {
                return entity;
            }
        }
        return new DefaultEntity();
    }

}
