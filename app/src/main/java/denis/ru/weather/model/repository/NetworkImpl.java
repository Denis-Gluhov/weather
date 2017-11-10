package denis.ru.weather.model.repository;

import android.support.annotation.NonNull;

import denis.ru.weather.model.Forecast10Day;
import denis.ru.weather.utils.Const;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class NetworkImpl implements Network {

    private NetworkService service;

    public NetworkImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);
    }

    @Override
    public Observable<Forecast10Day> getForecastFrom10(@NonNull String latitude, @NonNull String longitude) {
        return service.getForecast10Day(latitude, longitude);
    }
}
