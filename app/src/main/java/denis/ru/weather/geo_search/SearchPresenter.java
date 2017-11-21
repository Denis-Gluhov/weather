package denis.ru.weather.geo_search;

public class SearchPresenter implements SearchContract.Presenter {

    private final SearchContract.View view;
    private final SearchContract.Interactor interactor;

    public SearchPresenter(SearchContract.View view) {
        this.view = view;
        this.interactor = new SearchInteractor(this);
    }

    @Override
    public void onExit() {
        view.onExit();
    }
}
