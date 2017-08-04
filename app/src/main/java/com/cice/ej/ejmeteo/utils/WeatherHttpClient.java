package com.cice.ej.ejmeteo.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by EmilioJosé on 03/04/2017.
 */

public class WeatherHttpClient {

    //http://api.openweathermap.org/data/2.5/weather?q=Madrid&appid=db87faa1ae2eb9f5b56d2d2bd6e11ff2&units=metric&lang=es
    private static final String BASE_URL_BEGIN = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String BASE_URL_END ="&appid=db87faa1ae2eb9f5b56d2d2bd6e11ff2&units=metric&lang=es";
    private static final String IMG_URL = "http://openweathermap.org/img/w/";

    public String getWeatherData(String location) {
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(BASE_URL_BEGIN + location + BASE_URL_END)).openConnection();
            con.setRequestMethod("GET");
            //con.setDoInput(true);
            //con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null )
                buffer.append(line + "rn");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    public byte[] getImage(String code) {
        HttpURLConnection con = null ;
        InputStream is = null;
        try {
            con = (HttpURLConnection) ( new URL(IMG_URL + code+".png")).openConnection();
            con.setRequestMethod("GET");
            //con.setDoInput(true);
            //con.setDoOutput(true);
            con.connect();

            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(buffer) != -1)
                baos.write(buffer);

            is.close();
            con.disconnect();
            return baos.toByteArray();
        } catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close();
            } catch(Throwable t) {

            }
            try { con.disconnect();
            } catch(Throwable t) {}
        }

        return null;

    }
}
