package com.cice.ej.ejmeteo.model;
/**
 * Created by EmilioJosé on 31/03/2017.
 */

import android.util.Log;

import com.cice.ej.ejmeteo.utils.Utils;

import java.util.List;

public class Weather {

    private Coord coord;
    private List<CurrentCondition> currentConditionList = null;
    private String base;
    private Temperature temperature;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Integer dt;
    private Sys sys;
    private Integer id;
    private String name;
    private Integer cod;
    private byte[] iconData;

    /**Method that return coordenates.
     * Format: [latitude, longitud]
     *
     * @return String.
     */
    public String getCoordenadas(){
        if(this.coord.getLat()==Utils.ERROR_READING_DOUBLE_VALUE || this.coord.getLon()== Utils.ERROR_READING_DOUBLE_VALUE){
            return Utils.ERROR_READING_DOUBLE_VALUE+"";
        }
        return "["+this.coord.getLat()+", "+this.coord.getLon()+"]";
    }

    public String getTemperatureWithUnits(){
        if(this.temperature.getTemp()!=Utils.ERROR_READING_DOUBLE_VALUE) {
            return this.temperature.getTemp() + " " + Utils.UNITS_TEMPERATURE;
        }else{
            return this.temperature.getTemp().toString();
        }
    }

    public String getTemperatureMaxWithUnits(){
        if(this.temperature.getTempMax()!=Utils.ERROR_READING_DOUBLE_VALUE) {
            return this.temperature.getTempMax() + " " + Utils.UNITS_TEMPERATURE;
        }else{
            return this.temperature.getTempMax().toString();
        }
    }

    public String getTemperatureMinWithUnits(){
        if(this.temperature.getTempMin()!=Utils.ERROR_READING_DOUBLE_VALUE) {
            return this.temperature.getTempMin() + " " + Utils.UNITS_TEMPERATURE;
        }else{
            return this.temperature.getTempMin().toString();
        }
    }

    public String getPressureWithUnits(){
        if(this.temperature.getPressure()!=Utils.ERROR_READING_DOUBLE_VALUE) {
            return this.temperature.getPressure() + " " + Utils.UNITS_PRESSURE;
        }
        return this.temperature.getPressure().toString();
    }

    public String getHumidityWithUnits(){
        if(this.temperature.getHumidity()!=Utils.ERROR_READING_DOUBLE_VALUE) {
            return this.temperature.getHumidity() + " " + Utils.UNITS_HUMIDITY;
        }
        return this.temperature.getHumidity().toString();
    }

    public String getWindWithUnits(){
        if(this.wind.getSpeed() != Utils.ERROR_READING_DOUBLE_VALUE) {
            return this.wind.getSpeed() + " " + Utils.UNITS_WIND;
        }
        return this.wind.getSpeed().toString();
    }

    public String getCloudsAll(){
        return this.clouds.getAll().toString();
    }


    /**Constructor used for testing
     *
     * @param name
     */
    public Weather(String name) {
        this(null, null, null, null, null, null, null, null, null, null, name, null);
    }

    /**
     * @param id
     * @param dt
     * @param clouds
     * @param coord
     * @param wind
     * @param cod
     * @param visibility
     * @param sys
     * @param name
     * @param base
     * @param currentConditionList
     * @param temperature
     */
    public Weather(Coord coord, List<CurrentCondition> currentConditionList, String base,
                   Temperature temperature, Integer visibility, Wind wind, Clouds clouds,
                   Integer dt, Sys sys, Integer id, String name, Integer cod) {
        super();
        Log.d("Weather.java","name: "+name);
        this.coord = coord;
        this.currentConditionList = currentConditionList;
        this.base = base;
        this.temperature = temperature;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    private Coord getCoord() {
        return coord;
    }

    private List<CurrentCondition> getCurrentConditionList() {
        return currentConditionList;
    }

    public CurrentCondition getCurrentCondition(){
        if(currentConditionList!=null && currentConditionList.size()>0){
            return currentConditionList.get(0);
        }
        return null;
    }

    private String getBase() {
        return base;
    }

    private Temperature getTemperature() {
        return temperature;
    }

    private Integer getVisibility() {
        return visibility;
    }

    private Wind getWind() {
        return wind;
    }

    private Clouds getClouds() {
        return clouds;
    }

    private Integer getDt() {
        return dt;
    }

    private Sys getSys() {
        return sys;
    }

    private Integer getId() {
        return id;
    }

    public String getCityName() {
        return name;
    }

    private Integer getCod() {
        return cod;
    }

    public byte[] getIconData() {
        return iconData;
    }

    public void setIconData(byte[] iconData) {
        this.iconData = iconData;
    }
}
