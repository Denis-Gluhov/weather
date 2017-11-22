package denis.ru.weather;

import android.app.Application;

import denis.ru.weather.di.AppComponent;

public class App extends Application {

    private static App app;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App app() {
        return app;
    }

    public AppComponent getAppComponent() {
        return this.appComponent;
    }
}
