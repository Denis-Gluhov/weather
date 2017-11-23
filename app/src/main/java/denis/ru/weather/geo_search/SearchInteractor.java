package denis.ru.weather.geo_search;

import denis.ru.weather.repository.ForecastRepository;
import denis.ru.weather.repository.ForecastRepositoryImpl;

public class SearchInteractor implements SearchContract.Interactor {

    private final SearchContract.Presenter presenter;
    //private final ForecastRepository forecastRepository;

    public SearchInteractor(SearchContract.Presenter presenter) {
        this.presenter = presenter;
        //this.forecastRepository = new ForecastRepositoryImpl();
    }
}
