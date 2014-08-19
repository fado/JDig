package properties;

/**
 * JDig, a tool for the automatic generation of LPC class files for Epitaph
 * developers.  Copyright (C) 2014 Fado@Epitaph.
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

import java.util.Arrays;
import java.util.List;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Controls what logging events get appended to the console by Logback.
 */
public class ConsoleAppenderFilter extends AbstractMatcherFilter{

    @Override
    public FilterReply decide(Object event) {
        if(!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        // Cast the passed-in event as a LoggingEvent.
        LoggingEvent loggingEvent = (LoggingEvent) event;
        List<Level> eventsToKeep =
                Arrays.asList(Level.TRACE, Level.DEBUG, Level.INFO, Level.ERROR);
        // Check the level of the passed-in event against what we want to keep.
        if(eventsToKeep.contains(loggingEvent.getLevel())) {
            return FilterReply.NEUTRAL;
        } else {
            return FilterReply.DENY;
        }
    }

}
