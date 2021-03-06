/**
 * Collection of useful methods.
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
 * @package com.github.ruleant.getback_gps.lib
 * @author  Dieter Adriaenssens <ruleant@users.sourceforge.net>
 */
package com.github.ruleant.getback_gps.lib;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;

/**
 * Collection of useful methods.
 *
 * @author  Dieter Adriaenssens <ruleant@users.sourceforge.net>
 */
public class Tools {
    /**
     * Millisecond to nanosecond conversion rate.
     */
    public static final long MILLI_IN_NANO = 1000000;

    /**
     * Microsecond to nanosecond conversion rate.
     */
    public static final long MICRO_IN_NANO = 1000;

    /**
     * Seconds to milliseconds conversion rate.
     */
    public static final long SECOND_IN_MILLIS = 1000;

    /**
     * Hidden constructor, to prevent instantiating.
     */
    protected Tools() {
        // prevents calls from subclass
        throw new UnsupportedOperationException();
    }

    /**
     * Return biggest number.
     *
     * @param value1 one value
     * @param value2 another value
     * @return biggest number
     */
    public static long getMax(final long value1, final long value2) {
        if (value1 > value2) {
            return value1;
        } else {
            return value2;
        }
    }

    /**
     * Checks if timestamp (in milliseconds) is recent.
     *
     * @param timestamp timestamp in milliseconds
     * @param validity timestamp validity in milliseconds
     * @return true if timestamp is recent.
     */
    public static boolean isTimestampRecent(final long timestamp,
                                      final long validity) {
        // TODO test with intrumentation test
        return timestamp > 0
                && validity > 0
                && (SystemClock.elapsedRealtime() - timestamp) < validity;
    }

    /**
     * Checks if timestamp (in nanoseconds) is recent.
     *
     * @param timestamp timestamp in nanoseconds
     * @param validity timestamp validity in nanoseconds
     * @return true if timestamp is recent.
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean isTimestampNanoRecent(final long timestamp,
                                          final long validity) {
        // TODO test with intrumentation test
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return isTimestampRecent(timestamp / MILLI_IN_NANO,
                    validity / MILLI_IN_NANO);
        } else {
            // use elapsedRealtimeNanos when using API 17 or higher
            return timestamp > 0
                    && validity > 0
                    && (SystemClock.elapsedRealtimeNanos() - timestamp)
                    < validity;
        }
    }
}
