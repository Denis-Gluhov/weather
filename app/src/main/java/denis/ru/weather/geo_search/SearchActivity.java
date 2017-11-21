package denis.ru.weather.geo_search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import denis.ru.weather.R;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    private SearchContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_geo);
        presenter = new SearchPresenter(this);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_search_weather);
        toolbar.setTitle(this.getString(R.string.text_search_weather));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(v -> presenter.onExit());
    }

    @Override
    public void onExit() {
        finish();
    }
}
