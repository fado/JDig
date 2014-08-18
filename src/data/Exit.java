package data;

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

/**
 * An Exit, linking one Room to another Room.
 */
public class Exit {

    private final Direction direction;
    private final Room destination;
    private final ExitType exitType;

    /**
     * Default constructor.
     * @param direction The Direction the Exit is pointing.
     * @param destination The Room to which the Exit is pointing.
     * @param exitType The type of Exit, in MUD terms.
     */
    public Exit(Direction direction, Room destination, ExitType exitType) {
        this.direction = direction;
        this.destination = destination;
        this.exitType = exitType;
    }

    /**
     * Gets the Direction of the Exit.
     * @return the Direction of the Exit.
     */
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Gets the Room that is the Destination of the Exit.
     * @return the Room that is the Destination of the Exit.
     */
    public Room getDestination() {
        return this.destination;
    }

    /**
     * Gets the ExitType.
     * @return the ExitType
     */
    public ExitType getExitType() {
        return this.exitType;
    }

}
