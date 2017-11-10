package denis.ru.weather.screens.search.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import denis.ru.weather.R;
import denis.ru.weather.screens.search.presenter.SearchPresenter;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private SearchPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_weather);
        presenter = new SearchPresenter(this);
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar_search_weather);
        toolbar.setTitle(this.getString(R.string.text_search_weather));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickExit();
            }
        });
    }

    @Override
    public void onExit() {
        finish();
    }
}
