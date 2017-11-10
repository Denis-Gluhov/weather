package denis.ru.weather.screens.main.presenter;

import denis.ru.weather.screens.main.view.MainView;

public class MainPresenter {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void onClickFromOpenCurrentActivity() {
        mainView.onOpenCurrentActivity();
    }

    public void onClickFromOpenSearchActivity() {
        mainView.onOpenSearchActivity();
    }
}
