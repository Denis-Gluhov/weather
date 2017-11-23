package denis.ru.weather;

import android.app.Application;

import denis.ru.weather.di.AppComponent;
import denis.ru.weather.di.DaggerAppComponent;
import denis.ru.weather.di.NetworkModule;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
