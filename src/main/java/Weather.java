import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Weather {
    private final SimpleStringProperty date, partOfDay, propertyWeather,
            temp,pressure, moisture, wind;

    public Weather(String date,String partOfDay,String propertyWeather,
                       String temp, String pressure,String moisture,String wind){
        this.date = new SimpleStringProperty(date);
        this.partOfDay = new SimpleStringProperty(partOfDay);
        this.propertyWeather = new SimpleStringProperty(propertyWeather);
        this.temp = new SimpleStringProperty(temp);
        this.pressure = new SimpleStringProperty(pressure);
        this.moisture = new SimpleStringProperty(moisture);
        this.wind = new SimpleStringProperty(wind);
    }

    public String getDate() {
        return date.get();
    }


    public String getPartOfDay() {
        return partOfDay.get();
    }


    public String getPropertyWeather() {
        return propertyWeather.get();
    }


    public String getPressure() {
        return pressure.get();
    }


    public String getMoisture() {
        return moisture.get();
    }


    public String getWind() {
        return wind.get();
    }


    public String getTemp() {
        return temp.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public void setPartOfDay(String partOfDay) {
        this.partOfDay.set(partOfDay);
    }

    public void setPropertyWeather(String propertyWeather) {
        this.propertyWeather.set(propertyWeather);
    }

    public void setTemp(String temp) {
        this.temp.set(temp);
    }

    public void setPressure(String pressure) {
        this.pressure.set(pressure);
    }

    public void setMoisture(String moisture) {
        this.moisture.set(moisture);
    }

    public void setWind(String wind) {
        this.wind.set(wind);
    }



}
