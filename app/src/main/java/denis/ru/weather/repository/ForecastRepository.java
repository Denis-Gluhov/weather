package denis.ru.weather.repository;

import android.support.annotation.NonNull;

import denis.ru.weather.model.Forecast10Day;
import rx.Observable;

public interface ForecastRepository {

    Observable<Forecast10Day> getForecast10Day(@NonNull String latitude, @NonNull String longitude);

}
