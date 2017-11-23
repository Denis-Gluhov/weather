package denis.ru.weather.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import denis.ru.weather.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public final class LoggingInterceptor implements Interceptor {

    private final Interceptor loginInterceptor;

    public LoggingInterceptor() {
        loginInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    public static Interceptor create() {
        return new LoggingInterceptor();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return loginInterceptor.intercept(chain);
    }
}
