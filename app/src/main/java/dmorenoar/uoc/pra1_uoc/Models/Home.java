package dmorenoar.uoc.pra1_uoc.Models;

import android.graphics.Bitmap;

public class Home {
    private String cityName;
    private Bitmap cityImage;

    public Home(String cityName, Bitmap cityImage) {
        this.cityName = cityName;
        this.cityImage = cityImage;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Bitmap getCityImage() {
        return cityImage;
    }

    public void setCityImage(Bitmap cityImage) {
        this.cityImage = cityImage;
    }

    @Override
    public String toString() {
        return "Home{" +
                "cityName='" + cityName + '\'' +
                ", cityImage=" + cityImage +
                '}';
    }
}
