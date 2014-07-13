package main;

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

public class ExitModel {
    
    private final Direction direction;
    private final RoomModel destination;
    private final ExitType exitType;
    
    public ExitModel(Direction direction, RoomModel destination, ExitType exitType) {
        this.direction = direction;
        this.destination = destination;
        this.exitType = exitType;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public RoomModel getDestination() {
        return this.destination;
    }
    
    public ExitType getExitType() {
        return this.exitType;
    }
}