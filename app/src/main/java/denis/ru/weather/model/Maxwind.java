package denis.ru.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Developer on 6/25/2017.
 */

public class Maxwind {

    @SerializedName("mph")
    @Expose
    private Integer mph;
    @SerializedName("kph")
    @Expose
    private Integer kph;
    @SerializedName("dir")
    @Expose
    private String dir;
    @SerializedName("degrees")
    @Expose
    private Integer degrees;

    public Integer getMph() {
        return mph;
    }

    public void setMph(Integer mph) {
        this.mph = mph;
    }

    public Integer getKph() {
        return kph;
    }

    public void setKph(Integer kph) {
        this.kph = kph;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Integer getDegrees() {
        return degrees;
    }

    public void setDegrees(Integer degrees) {
        this.degrees = degrees;
    }

}
