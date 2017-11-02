package denis.ru.weather.network;

public class Constants {
    public static final String BASE_URL = "http://api.wunderground.com/";
    private static final String KEY = "2e651468b61897ae";
    public static final String FORECAST = "/api/" + KEY + "/forecast10day/q/";
    public static final String FORMAT = ".json";
}
