package denis.ru.weather.geo_main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import denis.ru.weather.geo_current.CurrentActivity;
import denis.ru.weather.geo_search.SearchActivity;

@Module
public class MainActivityModule {

    @NonNull
    private final MainContract.View view;

    public MainActivityModule(@NonNull MainContract.View view) {
        this.view = view;
    }

    @NonNull
    @MainActivityScoupe
    @Provides
    MainContract.View provideView() {
        return view;
    }

    @NonNull
    @MainActivityScoupe
    @Provides
    MainContract.Presenter providePresenter(@NonNull MainContract.View view,
                                            @NonNull MainContract.Presenter presenter) {
        return new MainPresenter(view);
    }

    @NonNull
    @Named("current")
    @MainActivityScoupe
    @Provides
    Intent provideIntentCurrentActivity(@NonNull MainContract.View view){
        return new Intent((Context) view, CurrentActivity.class);
    }

    @NonNull
    @Named("search")
    @MainActivityScoupe
    @Provides
    Intent provideIntentSearchActivity(@NonNull MainContract.View view) {
        return new Intent((Context) view, SearchActivity.class);
    }
}
