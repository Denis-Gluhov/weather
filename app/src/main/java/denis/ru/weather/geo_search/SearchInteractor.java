package denis.ru.weather.geo_search;

import denis.ru.weather.model.repository.Network;
import denis.ru.weather.model.repository.NetworkImpl;

public class SearchInteractor implements SearchContract.Interactor {

    private final SearchContract.Presenter presenter;
    private final Network network;

    public SearchInteractor(SearchContract.Presenter presenter) {
        this.presenter = presenter;
        this.network = new NetworkImpl();
    }
}
