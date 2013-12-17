GetBack GPS
===========

[![Build Status](https://travis-ci.org/ruleant/getback_gps.png?branch=master)](https://travis-ci.org/ruleant/getback_gps)
[![Coverage Status](https://coveralls.io/repos/ruleant/getback_gps/badge.png?branch=master)](https://coveralls.io/r/ruleant/getback_gps?branch=master)
[![Translation status](http://hosted.weblate.org/widgets/getback_gps-status-badge.png)](http://hosted.weblate.org/engage/getback_gps/?utm_source=widget)

Open Source Android app for finding your way back to a previously visited location using GPS coordinates.

Imagine visiting a town, going to an event or doing some hiking. When finished, you have to find your car again, or the way to the station, or any other point where you started.
Store a location when you start your trip, and at the end of the day, use the app to find your way back to where you started.

More info on our [website](http://ruleant.github.io/getback_gps).

Features
--------

- determine location based on GPS signal, WiFi or cell phone network :
    - use best/most accurate location provider
    - get location updates automatically (based on a time interval or change of location) or
    - get location on demand (by manually refreshing)
- save a location
- ‘get back’ mode : indicator pointing to a stored location (showing distance and direction)
- indicate current speed and current bearing


Bugs and feature requests
-------------------------

Please report bugs and add feature requests in the Github [issue tracker](https://github.com/ruleant/getback_gps/issues).

Requests for translations to a new language can be made here as well.

Translation
-----------

You can help translating the app to your own language : <http://hosted.weblate.org/projects/getback_gps/strings>.

[![Translation status](http://hosted.weblate.org/widgets/getback_gps-287x66-grey.png)](http://hosted.weblate.org/engage/getback_gps/?utm_source=widget)

Is your language not listed? Follow the instructions on the 'New translation' tab in Weblate or open an issue in the Github [issue tracker](https://github.com/ruleant/getback_gps/issues).

Code documentation
------------------

Code documentation of all classes and their methods can be found [here](doc/index.html).

Third party material
--------------------

- res/drawable-*/ic_action_refresh.png
    - From the Action Bar Icon Pack, see http://developer.android.com/design/style/iconography.html
    - origin : http://developer.android.com/downloads/design/Android_Design_Icons_20131106.zip
    - license : Apache 2.0

- Crouton library
    - version : v1.8.1
    - website : https://github.com/keyboardsurfer/Crouton
    - license : Apache 2.0

License
-------

Copyright (C) 2012-2013 Dieter Adriaenssens <ruleant@users.sourceforge.net>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
