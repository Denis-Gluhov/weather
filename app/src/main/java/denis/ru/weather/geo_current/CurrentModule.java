package denis.ru.weather.geo_current;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import denis.ru.weather.repository.ForecastRepository;

@Module
public class CurrentModule {

    private final CurrentContract.View view;

    public CurrentModule(CurrentContract.View view) {
        this.view = view;
    }

    @CurrentActivityScope
    @Provides
    CurrentContract.View provideView() {
        return view;
    }

    @CurrentActivityScope
    @Provides
    CurrentContract.Interactor provideInteractor(@NonNull Context context,
                                                 @NonNull ForecastRepository repository) {
        return new CurrentInteractor(context, repository);
    }

    @CurrentActivityScope
    @Provides
    CurrentContract.Presenter providePresenter(@NonNull CurrentContract.View view,
                                               @NonNull CurrentContract.Interactor interactor) {
        return new CurrentPresenter(view, interactor);
    }
}
