package denis.ru.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features {

    @SerializedName("forecast10day")
    @Expose
    private Integer forecast10day;

    public Integer getForecast10day() {
        return forecast10day;
    }

    public void setForecast10day(Integer forecast10day) {
        this.forecast10day = forecast10day;
    }

}
