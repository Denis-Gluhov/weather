package denis.ru.weather.screens.search.presenter;

import denis.ru.weather.screens.search.view.SearchView;

public class SearchPresenter {

    private SearchView searchView;

    public SearchPresenter(SearchView searchView) {
        this.searchView = searchView;
    }

    public void onClickExit() {
        searchView.onExit();
    }
}
