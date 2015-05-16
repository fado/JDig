package data;

import properties.ImageProperties;

/**
 * Created by Fado on 12/05/2015 for the Epitaph MUD.
 * Copyright (C) 2015 Fado@Epitaph.
 * Distributed under the GPL3 license.
 */
public enum ConnectionType {
    NONE(null),
    HORIZONTAL(new ImageProperties().getImagePath("HorizontalExit")),
    VERTICAL(new ImageProperties().getImagePath("VerticalExit")),
    FORWARD_DIAGONAL(new ImageProperties().getImagePath("ForwardDiagonalExit")),
    BACKWARD_DIAGONAL(new ImageProperties().getImagePath("BackwardDiagonalExit")),
    X(new ImageProperties().getImagePath("XExit"));

    private String exitImagePath;

    ConnectionType(String exitImagePath) {
         this.exitImagePath = exitImagePath;
    }

    public String getPath() {
        return this.exitImagePath;
    }

}
