package denis.ru.weather.repository;

import android.support.annotation.NonNull;

import denis.ru.weather.api.NetworkService;
import denis.ru.weather.model.Forecast10Day;
import rx.Observable;

public class ForecastRepositoryImpl implements ForecastRepository {

    @NonNull
    private NetworkService service;

    public ForecastRepositoryImpl(@NonNull NetworkService service) {
        this.service = service;
    }

    @Override
    public Observable<Forecast10Day> getForecast10Day(@NonNull String latitude, @NonNull String longitude) {
        return service.getForecast10Day(latitude, longitude);
    }
}
