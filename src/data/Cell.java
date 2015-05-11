package data;

import java.awt.Point;

/**
 * Created by Fado on 10/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public class Cell {

    public final int X;
    public final int Y;
    private Entity entity;

    public Cell(Point point) {
        this.X = point.x;
        this.Y = point.y;
    }

    public void setEntity(Entity entity) { this.entity = entity; }

    public Entity getEntity() { return this.entity; }

    public boolean isConnectible() { return entity != null && entity instanceof Connectible; }

    public boolean isExit() { return entity != null && !isConnectible(); }

    public boolean isInBounds() { return this.X >= 0 && this.Y >= 0; }

}
