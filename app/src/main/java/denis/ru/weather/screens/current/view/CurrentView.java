package denis.ru.weather.screens.current.view;

import denis.ru.weather.model.Forecast;

public interface CurrentView {
    void showIndicatorProgress();
    void hideIndicatorProgress();
    void refreshData(Forecast data);
    void requestPermission(String permissionFine, String permissionCoarse, int requestCode);
    void showError(String message);
}
