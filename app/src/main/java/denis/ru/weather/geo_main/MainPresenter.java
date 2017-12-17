package denis.ru.weather.geo_main;

import android.support.annotation.NonNull;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {

    @NonNull
    private final MainContract.View view;

    @Inject
    MainPresenter(@NonNull MainContract.View view) {
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
