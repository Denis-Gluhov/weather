package denis.ru.weather.dagger;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import denis.ru.weather.api.NetworkService;
import denis.ru.weather.repository.ForecastRepository;
import denis.ru.weather.repository.ForecastRepositoryImpl;
import denis.ru.weather.utils.Const;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    ForecastRepository provideForecastRepository(@NonNull NetworkService networkService) {
        return new ForecastRepositoryImpl(networkService);
    }

    @Provides
    @Singleton
    NetworkService provideNetworkService(@NonNull Retrofit retrofit) {
        return retrofit.create(NetworkService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

}
