package denis.ru.weather.geo_current;

import android.content.Context;
import android.support.annotation.NonNull;

import denis.ru.weather.model.Forecast;
import denis.ru.weather.repository.ForecastRepository;

public class CurrentPresenter implements CurrentContract.Presenter {

    private final CurrentContract.View view;
    private final CurrentContract.Interactor interactor;

    CurrentPresenter(@NonNull CurrentContract.View view, @NonNull Context context,
                     @NonNull ForecastRepository forecastRepository) {
        this.view = view;
        this.interactor = new CurrentInteractor(this, context, forecastRepository);
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
