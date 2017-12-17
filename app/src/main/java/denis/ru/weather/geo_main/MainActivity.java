package denis.ru.weather.geo_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import javax.inject.Inject;
import javax.inject.Named;

import denis.ru.weather.App;
import denis.ru.weather.BaseActivity;
import denis.ru.weather.R;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;
    @Named("current")
    @Inject
    Intent intentCurrentActivity;
    @Named("search")
    @Inject
    Intent intentSearchActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActivityComponent();
        Button btnCurrentWeather = (Button) this.findViewById(R.id.btn_current_weather);
        btnCurrentWeather.setOnClickListener(v -> presenter.openCurrentGeoActivity());
        Button btnSearchWeather = (Button) this.findViewById(R.id.btn_search_weather);
        btnSearchWeather.setOnClickListener(v -> presenter.openSearchGeoActivity());
    }

    @Override
    public void setupActivityComponent() {
        App.getInstance().initMainActivityComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        App.getInstance().destroyMainActivityComponent();
        super.onDestroy();
    }

    @Override
    public void onOpenCurrentGeoActivity() {
        startActivity(intentCurrentActivity);
    }

    @Override
    public void onOpenSearchGeoActivity() {
        startActivity(intentSearchActivity);
    }
}
