package com.cice.ej.ejmeteo.model;

/**
 * Created by EmilioJosé on 31/03/2017.
 */

public class Coord {

    private Double lon;
    private Double lat;

    /**
     *
     * @param lon
     * @param lat
     */
    public Coord(Double lon, Double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }

}
