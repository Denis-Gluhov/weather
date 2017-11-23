package denis.ru.weather.di;

import javax.inject.Singleton;

import dagger.Component;
import denis.ru.weather.geo_current.CurrentActivity;
import denis.ru.weather.geo_search.SearchActivity;

@Singleton
@Component (modules = NetworkModule.class)
public interface AppComponent {

    void inject(CurrentActivity currentActivity);

    void inject(SearchActivity searchActivity);

}
