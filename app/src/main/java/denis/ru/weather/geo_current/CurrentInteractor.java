package denis.ru.weather.geo_current;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

import denis.ru.weather.App;
import denis.ru.weather.R;
import denis.ru.weather.model.Forecast10Day;
import denis.ru.weather.model.repository.Network;
import denis.ru.weather.model.repository.NetworkImpl;
import denis.ru.weather.utils.LocationManager;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CurrentInteractor implements CurrentContract.Interactor {

    private static final String PERMISSION_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    private static final String PERMISSION_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    private static final int REQUEST_PERMISSION = 1;

    private final CurrentContract.Presenter presenter;
    @Inject
    Network network;
    private final Context context;

    public CurrentInteractor(@NonNull CurrentContract.Presenter presenter, @NonNull Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void loadData() {
        if (isPermissionGranted(PERMISSION_FINE_LOCATION, PERMISSION_COARSE_LOCATION)) {
            Observable<Forecast10Day> data = network.getForecastFrom10(String.valueOf(getCurrentLocation().getLatitude()),
                    String.valueOf(getCurrentLocation().getLongitude()));
            data.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(presenter::onShowProgress)
                    .doAfterTerminate(presenter::onHideProgress)
                    .subscribe(d -> presenter.onSetData(d.getForecast()),
                            throwable -> presenter.onShowError(throwable.getMessage()));

        } else {
            presenter.onRequestPermission(PERMISSION_FINE_LOCATION, PERMISSION_COARSE_LOCATION, REQUEST_PERMISSION);
        }
    }

    @Override
    public void responsePermission(int requestCode, @NonNull int[] grantResult) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED
                        && grantResult[1] == PackageManager.PERMISSION_GRANTED) {
                    loadData();
                } else {
                    presenter.onHideProgress();
                    presenter.onShowError(context.getString(R.string.text_access));
                }
                break;
        }
    }

    private Location getCurrentLocation() {
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

    private boolean isPermissionGranted(String permissionFine, String permissionCoarse) {
        int checkFine = ContextCompat.checkSelfPermission(context, permissionFine);
        int checkCoarse = ContextCompat.checkSelfPermission(context, permissionCoarse);
        return checkFine == PackageManager.PERMISSION_GRANTED && checkCoarse == PackageManager.PERMISSION_GRANTED;
    }
}
