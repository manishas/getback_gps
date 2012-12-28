/**
 * Main Activity
 * 
 * Copyright (C) 2012 Dieter Adriaenssens
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
 * @author Dieter Adriaenssens <ruleant@users.sourceforge.net>
 */
package org.ruleant.ariadne;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.ruleant.ariadne.LocationService.LocationBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Main Activity class
 * 
 * @author Dieter Adriaenssens <ruleant@users.sourceforge.net>
 */
public class MainActivity extends Activity {
	/**
	 * Interface to LocationService instance
	 */
	LocationService mService;
	/**
	 * Connection state with LocationService
	 */
	boolean mBound = false;
	private LocationManager locationManager;
	private String providerName = "";
	private Location location = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		locationManager =
				(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		if (! getLocationProvider().isEmpty()) {
			location = locationManager.getLastKnownLocation(providerName);
			refreshInterface();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Bind to LocalService
		Intent intent = new Intent(this, LocationService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
	}

	@Override
	public void onStop() {
		super.onStop();
		// Unbind from the service
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
	}

	/**
	 * Called when the user clicks the Renew provider button
	 * 
	 */
	public void renewProvider(View view) {
		getLocationProvider();
		refreshInterface();
	}

	/**
	 * Called when the user clicks the Renew location button
	 *
	 */
	public void renewLocation(View view) {
		getLocation();
		refreshInterface();
	}
	
	/**
	 * Retrieve Location Provider
	 *
	 * Define best location provider based on certain criteria
	 *
	 * @return String
	 */
	private String getLocationProvider() {
		// Retrieve a list of location providers that have fine accuracy, no monetary cost, etc
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setCostAllowed(false);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		
		if (locationManager != null) {
			providerName = locationManager.getBestProvider(criteria, true);
		}
		
		return providerName;
	}

	/**
	 * Retrieve Location
	 *
	 * Get last known location
	 *
	 * @return Location
	 */
	private Location getLocation() {
		if (locationManager == null || providerName.isEmpty()) {
			return null;
		}
		// todo : use more accurate location (with listener object)
		location = locationManager.getLastKnownLocation(providerName);

		return location;
	}

	/**
	 * refresh interface messages
	 * 
	 * Refresh the values of Location Provider, Location, ...
	 * 
	 * @return void
	 */
	private void refreshInterface() {
		// Refresh locationProvider
		TextView tv_provider = (TextView) findViewById(R.id.textView_LocationProvider);
		String providerText = getResources().getString(R.string.location_provider) + ": ";
		if (providerName.isEmpty()) {
			providerText += getResources().getString(R.string.none);
		} else {
			providerText += providerName;
		}
		tv_provider.setText(providerText);

		// Refresh Location
		TextView tv_location = (TextView) findViewById(R.id.textView_Location);
		String locationText = getResources().getString(R.string.location) + ":\n";
		if (location == null) {
			locationText += getResources().getString(R.string.unknown);
		} else {
			// Format location
			locationText += getResources().getString(R.string.latitude) + ": ";
			locationText += location.getLatitude() + "°\n";
			locationText += getResources().getString(R.string.longitude) + ": ";
			locationText += location.getLongitude() + "°\n";
			if (location.hasAltitude()) {
				locationText += getResources().getString(R.string.altitude) + ": ";
				locationText += location.getAltitude() + "m\n";
			}
			if (location.hasBearing()) {
				locationText += getResources().getString(R.string.bearing) + ": ";
				locationText += location.getBearing() + "°\n";
			}
			if (location.hasSpeed()) {
				locationText += getResources().getString(R.string.speed) + ": ";
				locationText += location.getSpeed() + "m/s\n";
			}
			if (location.hasAccuracy()) {
				locationText += getResources().getString(R.string.accuracy) + ": ";
				locationText += location.getAccuracy() + "m\n";
			}

			// Format Timestamp
			Date date = new Date(location.getTime());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSSz");
			locationText += getResources().getString(R.string.timestamp) + ": ";
			locationText += formatter.format(date) + "\n\n";

			// raw
			locationText += getResources().getString(R.string.raw) + ": ";
			locationText += location.toString();
		}
		tv_location.setText(locationText);
	}

	/** Defines callbacks for service binding, passed to bindService() */
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			// We've bound to LocalService, cast the IBinder and get LocalService instance
            LocationBinder binder = (LocationBinder) service;
            mService = binder.getService();
            mBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			mBound = false;
		}
	};
}