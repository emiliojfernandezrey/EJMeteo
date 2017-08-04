package com.cice.ej.ejmeteo.model;

/**
 * Created by EmilioJosé on 31/03/2017.
 */

public class Wind {

    private Double speed;
    private Integer deg;

    /**
     *
     * @param speed
     * @param deg
     */
    public Wind(Double speed, Integer deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public Double getSpeed() {
        return speed;
    }

    public Integer getDeg() {
        return deg;
    }

}
