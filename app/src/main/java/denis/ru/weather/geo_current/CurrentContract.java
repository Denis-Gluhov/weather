package denis.ru.weather.geo_current;

import android.support.annotation.NonNull;

import denis.ru.weather.model.Forecast;

public interface CurrentContract {

    interface View {
        void showIndicatorProgress();
        void hideIndicatorProgress();
        void setData(@NonNull Forecast data);
        void requestPermission(@NonNull String permissionFine, @NonNull String permissionCoarse, int requestCode);
        void showError(@NonNull String message);
    }

    interface Presenter {
        void onLoad();
        void onRefresh();
        void onResponsePermission(int requestCode, @NonNull int[] grantResult);
        void onShowError(@NonNull String message);
        void onShowProgress();
        void onHideProgress();
        void onSetData(@NonNull Forecast data);
        void onRequestPermission(@NonNull String permissionFine, @NonNull String permissionCoarse, int requestCode);
    }

    interface Interactor {
        void loadData();
        void responsePermission(int requestCode, @NonNull int[] grantResult);
    }

}
