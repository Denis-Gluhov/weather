package denis.ru.weather.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public final class LocationManager implements LocationListener {

    private static final float MIN_DISTANCE = 1000; // meters
    private static final long MIN_TIME = 10000; // milliseconds
    private final Context context;

    private android.location.LocationManager locationManager;
    static private Location lastKnownLocation;
    private boolean isStarted = false;

    private static LocationManager instance;

    public static LocationManager getInstance(Context context) {
        if (instance == null)
            instance = new LocationManager(context);
        return instance;
    }

    private LocationManager(Context context) {
        this.context = context;
        //Log.d("LOCATION_MANAGER","LocationManager");
        this.locationManager = (android.location.LocationManager) this.context.getSystemService(Context.LOCATION_SERVICE);
    }

    public void startLocationListening() {
        isStarted = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && this.context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                for (String provider : locationManager.getAllProviders()) {

                    locationManager.requestLocationUpdates(provider, MIN_TIME, MIN_DISTANCE, this);
                    Location location = locationManager.getLastKnownLocation(provider);
                    if (location != null) {
                        onLocationChanged(location);
                    }
                }
            }
        }

        //Log.d("LOCATION_MANAGER","startLocationListening");
    }

    public void stopLocationListening() {
        if (isStarted && locationManager != null) {
            locationManager.removeUpdates(this);
            isStarted = false;
        }
        //Log.d("LOCATION_MANAGER","stopLocationListening");
    }

    @Nullable
    public Location getLastKnownLocation() {
        if (lastKnownLocation != null) {
            /*Log.d("LAST CHANGE", "PROVIDER "+lastKnownLocation.getProvider());
            Log.d("LAST LOCATION", "LATLNG " + lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude());
            Log.d("LAST LOCATION", "SPEED " + lastKnownLocation.getSpeed());
            Log.d("LAST LOCATION", "BEARING " + lastKnownLocation.getBearing());*/

            return lastKnownLocation;
        }
        else
            return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        /*Log.d("LOCATION CHANGE", "PROVIDER "+location.getProvider());
        Log.d("LOCATION CHANGE", "LATLNG " + location.getLatitude() + " " + location.getLongitude());
        Log.d("LOCATION CHANGE", "SPEED " + location.getSpeed());
        Log.d("LOCATION CHANGE", "BEARING " + location.getBearing());*/

        if (isBetterLocation(location, lastKnownLocation)) {
            lastKnownLocation = location;
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (this.context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && this.context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(s, MIN_TIME, MIN_DISTANCE, this);
            }
        }
    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private static final int DELTA = 1000 * 5;

    private boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            return true;
        }
        if (location == null)
            return false;

        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > DELTA;
        boolean isSignificantlyOlder = timeDelta < -DELTA;
        boolean isNewer = timeDelta > 0;


        if (isSignificantlyNewer) {
            return true;
        } else if (isSignificantlyOlder) {
            return false;
        }
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }
}
