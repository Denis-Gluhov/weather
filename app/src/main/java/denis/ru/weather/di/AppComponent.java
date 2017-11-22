package denis.ru.weather.di;

import javax.inject.Singleton;

import dagger.Component;
import denis.ru.weather.geo_current.CurrentActivity;

@Singleton
@Component (modules = CurrentGeoModule.class)
public interface AppComponent {
    void inject(CurrentActivity currentActivity);
}
