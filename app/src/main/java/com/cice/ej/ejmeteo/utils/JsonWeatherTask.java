package com.cice.ej.ejmeteo.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cice.ej.ejmeteo.FragmentoMeteo;
import com.cice.ej.ejmeteo.R;
import com.cice.ej.ejmeteo.model.Weather;

import org.json.JSONException;

/**
 * Created by EmilioJosé on 03/04/2017.
 */

public class JsonWeatherTask extends AsyncTask<String, Void, Weather> {

    private FragmentoMeteo fragmentoMeteo;

    public void setFragmentoMeteo(FragmentoMeteo fragmentoMeteo){
        this.fragmentoMeteo=fragmentoMeteo;
    }

    @Override
    protected Weather doInBackground(String... params) {
        Weather weather = null;
        WeatherHttpClient weatherHttpClient=new WeatherHttpClient();
        String data = weatherHttpClient.getWeatherData(params[0]);

        Log.d("Valor: ", data);
        try {
            JsonWeatherParser jsonWeatherParser = new JsonWeatherParser(data);
            weather = jsonWeatherParser.getWeather();
            String code = weather.getCurrentCondition().getIcon();
            weatherHttpClient = new WeatherHttpClient();
            byte [] image = weatherHttpClient.getImage(code);
            weather.setIconData(image);
        }catch (JSONException jsone){
            Log.e("JSONException", jsone.toString());
        }catch (Exception e){
            Log.e("Exception", e.toString());
        }

        return weather;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        fragmentoMeteo.updateValues(weather);
    }


}
