package denis.ru.weather.geo_current;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import denis.ru.weather.model.Forecast;

public class CurrentPresenter implements CurrentContract.Presenter {

    private final CurrentContract.View view;
    private final CurrentContract.Interactor interactor;

    @Inject
    CurrentPresenter(@NonNull CurrentContract.View view,
                     @NonNull CurrentContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResponsePermission(int requestCode, @NonNull int[] grantResult) {
        interactor.responsePermission(requestCode, grantResult);
    }

    @Override
    public void onLoad() {
        view.showIndicatorProgress();
        interactor.loadData();
    }

    @Override
    public void onRefresh() {
        interactor.loadData();
    }

    @Override
    public void onShowError(@NonNull String message) {
        view.showError(message);
    }

    @Override
    public void onShowProgress() {
        view.showIndicatorProgress();
    }

    @Override
    public void onHideProgress() {
        view.hideIndicatorProgress();
    }

    @Override
    public void onSetData(@NonNull Forecast data) {
        view.setData(data);
    }

    @Override
    public void onRequestPermission(@NonNull String permissionFine, @NonNull String permissionCoarse, int requestCode) {
        view.requestPermission(permissionFine, permissionCoarse, requestCode);
    }
}
