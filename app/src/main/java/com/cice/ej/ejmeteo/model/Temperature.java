package com.cice.ej.ejmeteo.model;

/**
 * Created by EmilioJosé on 31/03/2017.
 */

public class Temperature {

    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Integer tempMin;
    private Integer tempMax;

    /**
     *
     * @param humidity
     * @param pressure
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Temperature(Double temp, Integer pressure, Integer humidity, Integer tempMin, Integer tempMax) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public Double getTemp() {
        return temp;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

}
