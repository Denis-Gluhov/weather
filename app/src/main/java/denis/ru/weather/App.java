package denis.ru.weather;

import android.app.Application;
import android.support.annotation.NonNull;

import denis.ru.weather.dagger.AppComponent;
import denis.ru.weather.dagger.AppModule;
import denis.ru.weather.dagger.DaggerAppComponent;
import denis.ru.weather.dagger.NetworkModule;
import denis.ru.weather.geo_current.CurrentActivity;
import denis.ru.weather.geo_current.CurrentActivityComponent;
import denis.ru.weather.geo_current.CurrentActivityModule;
import denis.ru.weather.geo_main.MainActivity;
import denis.ru.weather.geo_main.MainActivityComponent;
import denis.ru.weather.geo_main.MainActivityModule;

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private AppComponent appComponent;
    private CurrentActivityComponent currentActivityComponent;
    private MainActivityComponent mainActivityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger();
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public CurrentActivityComponent initCurrentComponent(@NonNull CurrentActivity activity) {
        currentActivityComponent = appComponent.currentComponent(new CurrentActivityModule(activity));
        return currentActivityComponent;
    }

    public MainActivityComponent initMainActivityComponent(@NonNull MainActivity activity) {
        mainActivityComponent = appComponent.mainActivityComponent(new MainActivityModule(activity));
        return mainActivityComponent;
    }

    public void destroyCurrentComponent() {
        currentActivityComponent = null;
    }

    public void destroyMainActivityComponent() {
        mainActivityComponent = null;
    }
}
