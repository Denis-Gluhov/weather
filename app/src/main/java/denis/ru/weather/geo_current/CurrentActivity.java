package denis.ru.weather.geo_current;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import denis.ru.weather.R;
import denis.ru.weather.adapter.ForecastAdapter;
import denis.ru.weather.model.Forecast;

public class CurrentActivity extends AppCompatActivity implements CurrentContract.View {

    private CurrentPresenter presenter;
    private ForecastAdapter forecastAdapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_geo);
        initToolbar();

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_current);
        swipeRefresh.setOnRefreshListener(() -> presenter.onRefresh());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_current);
        forecastAdapter = new ForecastAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(CurrentActivity.this));
        recyclerView.setAdapter(forecastAdapter);

        presenter = new CurrentPresenter(this, this);
        presenter.onLoad();
    }

    @Override
    public void showError(@NonNull String message) {
        AlertDialog.Builder dialogMessage = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(getString(R.string.text_ok), null);
        dialogMessage.create();
        dialogMessage.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.onResponsePermission(requestCode, grantResults);
    }

    @Override
    public void setData(@NonNull Forecast data) {
        forecastAdapter.setData(data);
        forecastAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIndicatorProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideIndicatorProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void requestPermission(@NonNull String permissionFine, @NonNull String permissionCoarse, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionFine, permissionCoarse}, requestCode);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_current_weather);
        toolbar.setTitle(this.getString(R.string.text_current_weather));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
