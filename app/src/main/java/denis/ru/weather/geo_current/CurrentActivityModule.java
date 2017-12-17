package denis.ru.weather.geo_current;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import denis.ru.weather.adapter.ForecastAdapter;
import denis.ru.weather.repository.ForecastRepository;

@Module
public class CurrentActivityModule {

    private final CurrentContract.View view;

    public CurrentActivityModule(CurrentContract.View view) {
        this.view = view;
    }

    @NonNull
    @CurrentActivityScope
    @Provides
    CurrentContract.View provideView() {
        return view;
    }

    @NonNull
    @CurrentActivityScope
    @Provides
    CurrentContract.Interactor provideInteractor(@NonNull Context context,
                                                 @NonNull ForecastRepository repository) {
        return new CurrentInteractor(context, repository);
    }

    @NonNull
    @CurrentActivityScope
    @Provides
    CurrentContract.Presenter providePresenter(@NonNull CurrentContract.View view,
                                               @NonNull CurrentContract.Interactor interactor) {
        return new CurrentPresenter(view, interactor);
    }

    @NonNull
    @CurrentActivityScope
    @Provides
    ForecastAdapter provideForecastAdapter(@NonNull Context context,
                                           @NonNull LayoutInflater layoutInflater) {
        return new ForecastAdapter(context, layoutInflater);
    }

    @NonNull
    @CurrentActivityScope
    @Provides
    LayoutInflater provideLayoutInflate(@NonNull Context context) {
        return LayoutInflater.from(context);
    }
}
