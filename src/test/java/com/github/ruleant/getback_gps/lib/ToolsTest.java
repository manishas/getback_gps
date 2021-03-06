/**
 * Unit tests for Tools class
 *
 * Copyright (C) 2014 Dieter Adriaenssens
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
 * @package com.github.ruleant.getback_gps
 * @author  Dieter Adriaenssens <ruleant@users.sourceforge.net>
 */
package com.github.ruleant.getback_gps.lib;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for FormatUtils class.
 *
 * @author  Dieter Adriaenssens <ruleant@users.sourceforge.net>
 */
@RunWith(RobolectricTestRunner.class)
public class ToolsTest {
    /**
     * Test value 1.
     */
    private static final long SMALL_VALUE = 10;

    /**
     * Test value 2.
     */
    private static final long BIG_VALUE = 100;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public final void setUp() {
    }

    /**
     * Test getMax() method.
     */
    @Test
    public final void testGetMax() {
        // equal values
        assertEquals(0, Tools.getMax(0, 0));
        assertEquals(1, Tools.getMax(1, 1));
        assertEquals(-1, Tools.getMax(-1, -1));

        // each parameter can be biggest
        assertEquals(1, Tools.getMax(0, 1));
        assertEquals(1, Tools.getMax(1, 0));
        assertEquals(0, Tools.getMax(0, -1));
        assertEquals(0, Tools.getMax(-1, 0));

        // a positive value is bigger than negative
        assertEquals(1, Tools.getMax(-1, 1));
        assertEquals(1, Tools.getMax(1, -1));

        // bigger numbers
        assertEquals(SMALL_VALUE, Tools.getMax(0, SMALL_VALUE));
        assertEquals(BIG_VALUE, Tools.getMax(0, BIG_VALUE));
        assertEquals(Long.MAX_VALUE, Tools.getMax(0, Long.MAX_VALUE));

        assertEquals(BIG_VALUE, Tools.getMax(BIG_VALUE, SMALL_VALUE));
        assertEquals(BIG_VALUE, Tools.getMax(SMALL_VALUE, BIG_VALUE));
        assertEquals(Long.MAX_VALUE,
                Tools.getMax(SMALL_VALUE, Long.MAX_VALUE));
        assertEquals(Long.MAX_VALUE, Tools.getMax(BIG_VALUE, Long.MAX_VALUE));

        // smaller numbers
        assertEquals(0, Tools.getMax(0, -1 * SMALL_VALUE));
        assertEquals(0, Tools.getMax(0, -1 * BIG_VALUE));
        assertEquals(0, Tools.getMax(0, Long.MIN_VALUE));

        assertEquals(-1 * SMALL_VALUE,
                Tools.getMax(-1 * BIG_VALUE, -1 * SMALL_VALUE));
        assertEquals(-1 * SMALL_VALUE,
                Tools.getMax(-1 * SMALL_VALUE, -1 * BIG_VALUE));
        assertEquals(-1 * SMALL_VALUE,
                Tools.getMax(-1 * SMALL_VALUE, Long.MIN_VALUE));
        assertEquals(-1 * BIG_VALUE,
                Tools.getMax(-1 * BIG_VALUE, Long.MIN_VALUE));
    }
}
