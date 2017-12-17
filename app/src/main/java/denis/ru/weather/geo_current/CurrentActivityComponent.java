package denis.ru.weather.geo_current;

import android.support.annotation.NonNull;

import dagger.Subcomponent;

@CurrentActivityScope
@Subcomponent(modules = CurrentActivityModule.class)
public interface CurrentActivityComponent {
    void inject(@NonNull CurrentActivity activity);
}
