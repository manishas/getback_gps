/**
 * Abstract class for formatting a geological coordinates.
 *
 * Copyright (C) 2012-2013 Dieter Adriaenssens
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @package org.ruleant.ariadne
 * @author  Dieter Adriaenssens <ruleant@users.sourceforge.net>
 */
package org.ruleant.ariadne;

/**
 * Abstract class for formatting a geological coordinates.
 *
 * @author  Dieter Adriaenssens <ruleant@users.sourceforge.net>
 */
public abstract class AbstractGeoCoordinate {
    /**
     * Converts an unformatted angle to a GeoCoordinate.
     *
     * @param unformattedAngle unformatted string
     * @return String formatted string
     */
    public String convert(final String unformattedAngle) {
        return null;
    }
}