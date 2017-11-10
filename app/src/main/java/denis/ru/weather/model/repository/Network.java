package denis.ru.weather.model.repository;

import android.support.annotation.NonNull;

import denis.ru.weather.model.Forecast10Day;
import rx.Observable;

public interface Network {
    Observable<Forecast10Day> getForecastFrom10(@NonNull String latitude, @NonNull String longitude);
}
