package denis.ru.weather.activities;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import denis.ru.weather.App;
import denis.ru.weather.LocationManager;
import denis.ru.weather.R;
import denis.ru.weather.adapter.ForecastAdapter;
import denis.ru.weather.model.Forecast10Day;

public class CurrentWeatherActivity extends AppCompatActivity {

    private static final String PERMISSION_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String PERMISSION_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int REQUEST_PERMISSION = 1;

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);
        initToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_current);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isPermissionGranted(PERMISSION_FINE_LOCATION, PERMISSION_COARSE_LOCATION)) {
            requestPermission(PERMISSION_FINE_LOCATION, PERMISSION_COARSE_LOCATION, REQUEST_PERMISSION);
        } else {
            startConnect();
        }
    }

    private boolean isPermissionGranted(String permissionFine, String permissionCoarse){
        int checkFine = ContextCompat.checkSelfPermission(this, permissionFine);
        int checkCoarse = ContextCompat.checkSelfPermission(this, permissionCoarse);
        return checkFine == PackageManager.PERMISSION_GRANTED && checkCoarse == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startConnect();
                } else {
                    Toast.makeText(this, getString(R.string.text_access), Toast.LENGTH_LONG).show();
                    finish();
                }
            break;
        }
    }

    private void startConnect(){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_load));
        dialog.setCancelable(false);
        dialog.show();

        String latitude = String.valueOf(getCurrentLocation().getLatitude());
        String longitude = String.valueOf(getCurrentLocation().getLongitude());

        App.getApi().getForecast10Day(latitude, longitude).enqueue(new Callback<Forecast10Day>() {
            @Override
            public void onResponse(Call<Forecast10Day> call, Response<Forecast10Day> response) {
                dialog.dismiss();
                ForecastAdapter adapter = new ForecastAdapter(CurrentWeatherActivity.this, response.body().getForecast());
                recyclerView.setLayoutManager(new LinearLayoutManager(CurrentWeatherActivity.this));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Forecast10Day> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(CurrentWeatherActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private Location getCurrentLocation(){
        Location currentLocation;
        LocationManager locationManager = LocationManager.getInstance(this);
        currentLocation = locationManager.getLastKnownLocation();
        if (currentLocation == null) {
            locationManager.startLocationListening();
            currentLocation = locationManager.getLastKnownLocation();
            locationManager.stopLocationListening();
        }
        return currentLocation;
    }

    private void requestPermission(String permissionFine, String permissionCoarse, int requestCode){
        ActivityCompat.requestPermissions(this, new String[]{permissionFine, permissionCoarse}, requestCode);
    }

    private void initToolbar(){
        toolbar = (Toolbar) this.findViewById(R.id.toolbar_current_weather);
        toolbar.setTitle(this.getString(R.string.text_current_weather));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
