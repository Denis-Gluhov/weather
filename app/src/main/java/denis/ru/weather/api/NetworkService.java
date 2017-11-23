package denis.ru.weather.api;


import android.support.annotation.NonNull;

import denis.ru.weather.model.Forecast10Day;
import denis.ru.weather.utils.Const;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface NetworkService {
    @GET(Const.FORECAST + "{latitude},{longitude}." + Const.FORMAT)
    Observable<Forecast10Day> getForecast10Day(@NonNull @Path("latitude") String latitude, @NonNull @Path("longitude") String longitude);
}
