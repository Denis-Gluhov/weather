package denis.ru.weather.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import denis.ru.weather.model.Forecast10Day;

public interface Api {
    @GET(Constants.FORECAST + "{latitude},{longitude}." + Constants.FORMAT)
    Call<Forecast10Day> getForecast10Day(@Path("latitude") String latitude, @Path("longitude") String longitude);
}
