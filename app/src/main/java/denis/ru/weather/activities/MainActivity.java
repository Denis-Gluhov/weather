package denis.ru.weather.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import denis.ru.weather.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        Button btnCurrentWeather = (Button) this.findViewById(R.id.btn_current_weather);
        btnCurrentWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CurrentWeatherActivity.class));
            }
        });
        Button btnSearchWeather = (Button) this.findViewById(R.id.btn_search_weather);
        btnSearchWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SearchWeatherActivity.class));
            }
        });
    }

    private void initToolbar(){
        toolbar = (Toolbar) this.findViewById(R.id.toolbar_main);
        toolbar.setTitle(this.getString(R.string.app_name));
    }
}
