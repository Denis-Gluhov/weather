package denis.ru.weather.dagger;

import javax.inject.Singleton;

import dagger.Component;
import denis.ru.weather.geo_current.CurrentActivityComponent;
import denis.ru.weather.geo_current.CurrentModule;

@Singleton
@Component (modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    CurrentActivityComponent currentComponent(CurrentModule module);
}
