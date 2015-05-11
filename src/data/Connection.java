package data;

import java.awt.Point;

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

