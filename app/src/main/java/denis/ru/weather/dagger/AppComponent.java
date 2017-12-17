package denis.ru.weather.dagger;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import denis.ru.weather.geo_current.CurrentActivityComponent;
import denis.ru.weather.geo_current.CurrentActivityModule;
import denis.ru.weather.geo_main.MainActivityComponent;
import denis.ru.weather.geo_main.MainActivityModule;

@Singleton
@Component (modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    MainActivityComponent mainActivityComponent(@NonNull MainActivityModule module);
    CurrentActivityComponent currentComponent(@NonNull CurrentActivityModule module);
}
