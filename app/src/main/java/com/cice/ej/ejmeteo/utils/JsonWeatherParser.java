package com.cice.ej.ejmeteo.utils;

import android.util.Log;

import com.cice.ej.ejmeteo.model.Clouds;
import com.cice.ej.ejmeteo.model.Coord;
import com.cice.ej.ejmeteo.model.CurrentCondition;
import com.cice.ej.ejmeteo.model.Sys;
import com.cice.ej.ejmeteo.model.Temperature;
import com.cice.ej.ejmeteo.model.Weather;
import com.cice.ej.ejmeteo.model.Wind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EmilioJosé on 03/04/2017.
 */


/** Example of text to parse:
 * {"coord":{"lon":-3.7,"lat":40.42},"weather":[{"id":800,"main":"Clear","description":"cielo claro","icon":"01d"}],"base":"stations","main":{"temp":16.78,"pressure":1023,"humidity":27,"temp_min":16,"temp_max":18},"visibility":10000,"wind":{"speed":1.5,"deg":310},"clouds":{"all":0},"dt":1491226200,"sys":{"type":1,"id":5488,"message":0.0265,"country":"ES","sunrise":1491198855,"sunset":1491244925},"id":3117735,"name":"Madrid","cod":200}rn
 */
public class JsonWeatherParser {

    private String data;
    private Weather weather;

    public JsonWeatherParser(String data) throws JSONException{
        this.data=data;

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        //Weather
        weather = getWeather(jObj);
    }

    public String getData() {
        return data;
    }

    public Weather getWeather() {
        return weather;
    }

    private Weather getWeather(JSONObject jObj) throws JSONException{
        Integer visibility, dt, id,cod;
        String name, base;
        try{
            base = jObj.getString("base");
        }catch(Exception e){
            base = Utils.ERROR_READING_STRING_BASE;
        }
        try {
            visibility = Integer.parseInt(jObj.get("visibility").toString());
        } catch(Exception e){
            visibility=Utils.ERROR_READING_INT_VALUE;
        }
        try {
            dt = Integer.parseInt(jObj.get("dt").toString());
        }catch (Exception e){
            dt = Utils.ERROR_READING_INT_VALUE;
        }
        try {
            id = Integer.parseInt(jObj.get("id").toString());
        }catch (Exception e){
            id=Utils.ERROR_READING_INT_VALUE;
        }
        try{
            name =jObj.get("name").toString();
        }catch(Exception e){
            name = Utils.ERROR_READING_STRING_CITY_NAME;
        }
        try {
            cod = Integer.parseInt(jObj.get("cod").toString());
        }catch(Exception e){
            cod= Utils.ERROR_READING_INT_VALUE;
        }

        //Coord
        Coord coord = getCoord(jObj);

        //Clouds
        Clouds clouds = getClouds(jObj);

        //CurrentCondition
        List<CurrentCondition> currentConditionList = getCurrentConditionList(jObj);

        //Sys
        Sys sys = getSys(jObj);

        //Temperature
        Temperature temperature = getTemperature(jObj);

        //Wind
        Wind wind = getWind(jObj);

        Log.d("Coord", coord.getLat()+" - "+coord.getLon());
        Log.d("Clouds", clouds.getAll().toString());
        Log.d("Sys",sys.getType()+ " - " + sys.getId() + " - " + sys.getMessage() + " - " +
                sys.getCountry() + " - " + sys.getSunrise() +" - " + sys.getSunset());
        Log.d("Temperature",temperature.getTemp() +" - "+ temperature.getPressure() + " - " +
                temperature.getHumidity() + " - " + temperature.getTempMin() + " - " + temperature.getTempMax());
        Log.d("Wind",wind.getSpeed()+ " - " + wind.getDeg());
        Log.d("El resto: ", "base: "+base+", visibility: "+visibility +", dt: "+dt +", id: "+id+ ", name: "+name +", code: "+cod);

        return new Weather(coord, currentConditionList, base, temperature, visibility, wind, clouds, dt, sys, id, name, cod);
    }

    private Coord getCoord(JSONObject jObj){
        Double lat, lon;
        try{
            lat = Double.parseDouble(jObj.getJSONObject("coord").get("lat").toString());
        }catch(Exception e){
            lat = Utils.ERROR_READING_DOUBLE_VALUE;
        }
        try{
            lon = Double.parseDouble(jObj.getJSONObject("coord").get("lon").toString());
        }catch(Exception e){
            lon = Utils.ERROR_READING_DOUBLE_VALUE;
        }
        return new Coord(lon,lat);
    }

    private Clouds getClouds(JSONObject jObj) {
        Integer all;
        try{
            all = Integer.parseInt(jObj.getJSONObject("clouds").get("all").toString());
        }catch(Exception e){
            all= Utils.ERROR_READING_INT_VALUE;
        }
        return new Clouds(all);
    }

    private ArrayList<CurrentCondition> getCurrentConditionList(JSONObject jObj)throws  JSONException {
        JSONArray jsonArray = (JSONArray) jObj.get("weather");
        ArrayList<CurrentCondition> list = new ArrayList<CurrentCondition>();
        Integer id;
        String condition, description;
        String icon;
        for(int i =0;i<jsonArray.length();i++){
            try{
                id = ((JSONObject) jsonArray.get(i)).getInt("id");
            }catch(Exception e){
                id=Utils.ERROR_READING_INT_VALUE;
            }
            try{
                condition = ((JSONObject) jsonArray.get(i)).getString("main");
            }catch(Exception e){
                condition = Utils.ERROR_READING_STRING_WEATHER_CONDITION;
            }
            try{
                description = ((JSONObject) jsonArray.get(i)).getString("description");
            }catch(Exception e){
                description = Utils.ERROR_READING_STRING_WEATHER_DESCRIPTION;
            }
            try{
                icon = ((JSONObject) jsonArray.get(i)).getString("icon");
            }catch(Exception e){
                icon = Utils.ERROR_READING_STRING_WEATHER_ICON;
            }

            CurrentCondition currentCondition = new CurrentCondition(id,condition,description,icon);
            list.add(currentCondition);

            Log.d("CurrentCondition", currentCondition.getId() +" - "+ currentCondition.getCondition() +
                    " - " + currentCondition.getDescription() + " - " + currentCondition.getIcon());
        }

        return list;
    }

    private Sys getSys(JSONObject jObj)throws  JSONException{
        Integer type,idSys, sunrise, sunset;
        Double message;
        try{
            type = Integer.parseInt(jObj.getJSONObject("sys").get("type").toString());
        }catch(Exception e){
            type = Utils.ERROR_READING_INT_VALUE;
        }
        try{
            idSys = Integer.parseInt(jObj.getJSONObject("sys").get("id").toString());
        }catch(Exception e){
            idSys = Utils.ERROR_READING_INT_VALUE;
        }
        try{
            message = Double.parseDouble(jObj.getJSONObject("sys").get("message").toString());
        }catch(Exception e){
            message = Utils.ERROR_READING_DOUBLE_VALUE;
        }
        try{
            sunrise = Integer.parseInt(jObj.getJSONObject("sys").get("sunrise").toString());
        }catch(Exception e){
            sunrise = Utils.ERROR_READING_INT_VALUE;
        }
        try{
            sunset =Integer.parseInt(jObj.getJSONObject("sys").get("sunset").toString());
        }catch(Exception e){
            sunset = Utils.ERROR_READING_INT_VALUE;
        }

        String country = jObj.getJSONObject("sys").get("country").toString();

        return new Sys(type,idSys,message,country,sunrise,sunset);
    }

    private Temperature getTemperature(JSONObject jObj){
        Double temp;
        Integer pressure, humidity, tempMin, tempMax;
        try{
            temp = Double.parseDouble(jObj.getJSONObject("main").get("temp").toString());
        }catch(Exception e){
            temp = Utils.ERROR_READING_DOUBLE_VALUE;
        }
        try{
            pressure = Integer.parseInt(jObj.getJSONObject("main").get("pressure").toString());
        }catch(Exception e){
            pressure = Utils.ERROR_READING_INT_VALUE;
        }
        try{
            humidity = Integer.parseInt(jObj.getJSONObject("main").get("humidity").toString());
        }catch(Exception e){
            humidity = Utils.ERROR_READING_INT_VALUE;
        }
        try{
            tempMin = Integer.parseInt(jObj.getJSONObject("main").get("temp_min").toString());
        }catch(Exception e){
            tempMin = Utils.ERROR_READING_INT_VALUE;
        }
        try{
            tempMax = Integer.parseInt(jObj.getJSONObject("main").get("temp_max").toString());
        }catch(Exception e){
            tempMax = Utils.ERROR_READING_INT_VALUE;
        }

        return new Temperature(temp, pressure, humidity, tempMin, tempMax);
    }

    private Wind getWind(JSONObject jObj)throws  JSONException{
        Double speed;
        Integer deg;
        try{
            speed = Double.parseDouble(jObj.getJSONObject("wind").get("speed").toString());
        }catch(Exception e){
            speed = Utils.ERROR_READING_DOUBLE_VALUE;
        }
        try{
            deg = Integer.parseInt(jObj.getJSONObject("wind").get("deg").toString());
        }catch(Exception e){
            deg=Utils.ERROR_READING_INT_VALUE;
        }

        return new Wind(speed,deg);
    }
}
