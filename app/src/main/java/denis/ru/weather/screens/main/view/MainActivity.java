package denis.ru.weather.screens.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import denis.ru.weather.R;
import denis.ru.weather.screens.current.view.CurrentActivity;
import denis.ru.weather.screens.main.presenter.MainPresenter;
import denis.ru.weather.screens.search.view.SearchActivity;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        initToolbar();

        Button btnCurrentWeather = (Button) this.findViewById(R.id.btn_current_weather);
        btnCurrentWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickFromOpenCurrentActivity();
            }
        });
        Button btnSearchWeather = (Button) this.findViewById(R.id.btn_search_weather);
        btnSearchWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickFromOpenSearchActivity();
            }
        });
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_main);
        toolbar.setTitle(this.getString(R.string.app_name));
    }

    @Override
    public void onOpenCurrentActivity() {
        startActivity(new Intent(MainActivity.this, CurrentActivity.class));
    }

    @Override
    public void onOpenSearchActivity() {
        startActivity(new Intent(MainActivity.this, SearchActivity.class));
    }
}
