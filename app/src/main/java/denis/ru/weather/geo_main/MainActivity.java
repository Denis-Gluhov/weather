package denis.ru.weather.geo_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import denis.ru.weather.R;
import denis.ru.weather.geo_current.CurrentActivity;
import denis.ru.weather.geo_search.SearchActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);

        Button btnCurrentWeather = (Button) this.findViewById(R.id.btn_current_weather);
        btnCurrentWeather.setOnClickListener(v -> presenter.openCurrentGeoActivity());
        Button btnSearchWeather = (Button) this.findViewById(R.id.btn_search_weather);
        btnSearchWeather.setOnClickListener(v -> presenter.openSearchGeoActivity());
    }

    @Override
    public void onOpenCurrentGeoActivity() {
        startActivity(new Intent(this, CurrentActivity.class));
    }

    @Override
    public void onOpenSearchGeoActivity() {
        startActivity(new Intent(this, SearchActivity.class));
    }
}
