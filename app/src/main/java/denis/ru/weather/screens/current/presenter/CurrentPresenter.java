package denis.ru.weather.screens.current.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import denis.ru.weather.R;
import denis.ru.weather.model.Forecast10Day;
import denis.ru.weather.model.repository.Network;
import denis.ru.weather.model.repository.NetworkImpl;
import denis.ru.weather.screens.current.view.CurrentView;
import denis.ru.weather.utils.LocationManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CurrentPresenter {

    private static final String PERMISSION_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String PERMISSION_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int REQUEST_PERMISSION = 1;

    private CurrentView currentView;
    private Network network;
    private Context context;

    public CurrentPresenter(CurrentView currentView) {
        this.currentView = currentView;
        context = (Context) currentView;
        network = new NetworkImpl();
    }

    public void onResponsePermission(int requestCode, @NonNull int[] grantResult) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED
                        && grantResult[1] == PackageManager.PERMISSION_GRANTED) {
                    load();
                } else {
                    currentView.hideIndicatorProgress();
                    currentView.showError(context.getString(R.string.text_access));
                }
            break;
        }
    }

    public void load() {
        if (isPermissionGranted(PERMISSION_FINE_LOCATION, PERMISSION_COARSE_LOCATION)) {

            Observable<Forecast10Day> data = network.getForecastFrom10(String.valueOf(getCurrentLocation().getLatitude()),
                    String.valueOf(getCurrentLocation().getLongitude()));
            data.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(currentView::showIndicatorProgress)
                    .doAfterTerminate(currentView::hideIndicatorProgress)
                    .subscribe(this::blablabla,
                            throwable -> currentView.showError(throwable.getMessage()));

        } else {
            currentView.requestPermission(PERMISSION_FINE_LOCATION, PERMISSION_COARSE_LOCATION, REQUEST_PERMISSION);
        }
    }

    private void blablabla(Forecast10Day forecast10Day) {

    }

    private Location getCurrentLocation(){
        Location currentLocation;
        LocationManager locationManager = LocationManager.getInstance(context);
        currentLocation = locationManager.getLastKnownLocation();
        if (currentLocation == null) {
            locationManager.startLocationListening();
            currentLocation = locationManager.getLastKnownLocation();
            locationManager.stopLocationListening();
        }
        return currentLocation;
    }

    private boolean isPermissionGranted(String permissionFine, String permissionCoarse){
        int checkFine = ContextCompat.checkSelfPermission(context, permissionFine);
        int checkCoarse = ContextCompat.checkSelfPermission(context, permissionCoarse);
        return checkFine == PackageManager.PERMISSION_GRANTED && checkCoarse == PackageManager.PERMISSION_GRANTED;
    }
}
