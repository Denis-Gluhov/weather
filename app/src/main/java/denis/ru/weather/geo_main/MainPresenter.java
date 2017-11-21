package denis.ru.weather.geo_main;

public class MainPresenter implements MainContract.Presenter {

    private final MainContract.View view;

    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void openCurrentGeoActivity() {
        view.onOpenCurrentGeoActivity();
    }

    @Override
    public void openSearchGeoActivity() {
        view.onOpenSearchGeoActivity();
    }
}
