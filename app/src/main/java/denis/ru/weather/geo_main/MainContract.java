package denis.ru.weather.geo_main;

public interface MainContract {

    interface View {
        void onOpenCurrentGeoActivity();
        void onOpenSearchGeoActivity();
    }

    interface Presenter {
        void openCurrentGeoActivity();
        void openSearchGeoActivity();
    }

}
