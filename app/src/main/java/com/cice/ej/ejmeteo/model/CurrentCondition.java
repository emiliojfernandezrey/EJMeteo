package com.cice.ej.ejmeteo.model;

/**
 * Created by EmilioJosé on 31/03/2017.
 */

public class CurrentCondition {

    private Integer id;
    private String condition;
    private String description;
    private String icon;

    /**
     *
     * @param id
     * @param icon
     * @param description
     * @param condition
     */
    public CurrentCondition(Integer id, String condition, String description, String icon) {
        this.id = id;
        this.condition = condition;
        this.description = description;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public String getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

}
