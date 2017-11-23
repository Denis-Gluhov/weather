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

    //    public ForecastRepositoryImpl() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Const.BASE_URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        service = retrofit.create(NetworkService.class);
//    }

    @Override
    public Observable<Forecast10Day> getForecast10Day(@NonNull String latitude, @NonNull String longitude) {
        return service.getForecast10Day(latitude, longitude);
    }
}
