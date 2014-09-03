package tools;

/**
 * JDig, a mapTool for the automatic generation of LPC class files for Epitaph
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

import java.util.EventObject;

/**
 * Event fired when the currently selected level tool is changed.
 */
public class LevelToolEvent extends EventObject {
    private final LevelTool levelTool;

    /**
     * Constructor.
     * @param source Source of the event firing.
     * @param levelTool The newly selected level tool.
     */
    public LevelToolEvent(Object source, LevelTool levelTool) {
        super(source);
        this.levelTool = levelTool;
    }

    /**
     * Returns the newly selected level tool.
     * @return the newly selected level tool.
     */
    public LevelTool getLevelTool() {
        return levelTool;
    }

}
