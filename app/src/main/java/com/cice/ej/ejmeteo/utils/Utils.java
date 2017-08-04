package com.cice.ej.ejmeteo.utils;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by EmilioJosé on 02/04/2017.
 */

public class Utils {

    private static ArrayList<String> citiesList = new ArrayList<String>(Arrays.asList(
            "A Coruña","Álava","Albacete","Alicante","Almería","Oviedo","Ávila","Badajoz","Palma de Mallorca","Barcelona",
            "Burgos","Cáceres","Cádiz","Santander","Castellón De La Plana","Ciudad Real","Córdoba","Cuenca","Gerona","Granada",
            "Guadalajara","San Sebastián","Huelva","Huesca","Jaén","Logroño","Las Palmas De Gran Canaria","León","Lleida","Lugo",
            "Madrid","Málaga","Murcia","Pamplona","Orense","Palencia","Pontevedra","Salamanca","Segovia",
            "Sevilla","Soria","Tarragona","Santa Cruz de Tenerife","Teruel","Toledo","Valencia","Valladolid",
            "Bilbao","Zamora","Zaragoza"));

    private static final String fontBalooBhaina="fonts/BalooBhaina.ttf";
    private static final String fontIndieFlower="fonts/IndieFlower.ttf";
    private static final String myFont= fontBalooBhaina;
    private static Typeface typeFace=null;
    private static final int ERROR_READING_VALUE =-99999;
    public static final int ERROR_READING_INT_VALUE=ERROR_READING_VALUE;
    public static final double ERROR_READING_DOUBLE_VALUE=ERROR_READING_VALUE;
    public static final String ERROR_READING_STRING_WEATHER_ICON =ERROR_READING_VALUE+"";
    public static final String ERROR_READING_STRING_CITY_NAME = ERROR_READING_VALUE+"";
    public static final String ERROR_READING_STRING_BASE = ERROR_READING_VALUE+"";
    public static final String ERROR_READING_STRING_WEATHER_CONDITION = ERROR_READING_VALUE +"";
    public static final String ERROR_READING_STRING_WEATHER_DESCRIPTION =ERROR_READING_VALUE +"";
    private static Activity myActivity;
    public static final String UNITS_TEMPERATURE ="ºC";
    public static final String UNITS_PRESSURE="hpa";
    public static final String UNITS_HUMIDITY ="%";
    public static final String UNITS_WIND="m/s";
    public static final int DEFAULT_FONT_SIZE= 20;
    public static final int DEFAULT_TEMPERATURE_SIZE= 15;
    public static final String KEY_CITY_NAME= "CITY_NAME";


    public static void setActivity(Activity activity){
        myActivity =activity;
    }
/*
    public static Activity getActivity(){
        return myActivity;
    }
    */

    public static ArrayList<String> getCitiesList(){
        Collections.sort(citiesList);
        return citiesList;
    }

    public static int getCityPosition(String cityName){
        ArrayList<String> ary =  getCitiesList();
        for(int i=0;i<ary.size();i++){
            if(ary.get(i).equalsIgnoreCase(cityName)){
                return i;
            }
        }
        return 0;
    }

    public static void setComponentFont(TextView component){
        if(typeFace==null) {
            typeFace = Typeface.createFromAsset(myActivity.getAssets(), myFont);
        }
        component.setTypeface(typeFace,Typeface.BOLD);
    }

    public static void setSize(TextView component,int size){
        component.setTextSize(size);
    }

    public static void setComponentSize(TextView component, int size){
        component.setTextSize(size);
    }

    public static void setComponentFontAndSize(TextView component, int size){
        setComponentFont(component);
        setComponentSize(component, size);
    }

    public static void setComponentFontAndDefaultSize(TextView component){
        setComponentFontAndSize(component, DEFAULT_FONT_SIZE);
    }

    public static void setComponentsVisible(boolean visible, TextView textView1, TextView textView2){
        setComponentVisible(visible, textView1);
        setComponentVisible(visible, textView2);
    }
    private static void setComponentVisible(boolean visible, TextView component){
        if(!visible) {
            component.setText("");
        }
    }
}
