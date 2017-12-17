package denis.ru.weather.geo_current;

import dagger.Subcomponent;

@CurrentActivityScope
@Subcomponent(modules = CurrentModule.class)
public interface CurrentActivityComponent {
    void inject(CurrentActivity activity);
}
