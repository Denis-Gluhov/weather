package denis.ru.weather.geo_main;

import android.support.annotation.NonNull;

import dagger.Subcomponent;

@MainActivityScoupe
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(@NonNull MainActivity mainActivity);
}
