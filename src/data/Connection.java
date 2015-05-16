package data;

import java.awt.Point;

/**
 * Created by Fado on 12/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class Connection extends Entity {

    private ConnectionType connectionType;

    public Connection(Point point, ConnectionType connectionType) {
        this.X = point.x;
        this.Y = point.y;
        this.connectionType = connectionType;
    }

    public ConnectionType getConnectionType() {
        return this.connectionType;
    }

    @Override
    boolean isConnectible() {
        return false;
    }

}

