package denis.ru.weather.screens.current.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import denis.ru.weather.R;
import denis.ru.weather.adapter.ForecastAdapter;
import denis.ru.weather.model.Forecast;
import denis.ru.weather.screens.current.presenter.CurrentPresenter;

public class CurrentActivity extends AppCompatActivity implements CurrentView {

    private CurrentPresenter presenter;
    private ProgressDialog progressDialog;
    private ForecastAdapter forecastAdapter;
    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);
        initToolbar();

        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_current);
        swipeRefresh.setOnRefreshListener(() -> presenter.load());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_current);
        forecastAdapter = new ForecastAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(CurrentActivity.this));
        recyclerView.setAdapter(forecastAdapter);

        presenter = new CurrentPresenter(this);
        presenter.load();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        presenter.onResponsePermission(requestCode, grantResults);
    }

    @Override
    public void refreshData(Forecast data) {
        forecastAdapter.setData(data);
        forecastAdapter.notifyDataSetChanged();
    }

    @Override
    public void showIndicatorProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_load));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideIndicatorProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
        if (swipeRefresh != null)
            swipeRefresh.setRefreshing(false);
    }

    @Override
    public void requestPermission(String permissionFine, String permissionCoarse, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionFine, permissionCoarse}, requestCode);
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_current_weather);
        toolbar.setTitle(this.getString(R.string.text_current_weather));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}
