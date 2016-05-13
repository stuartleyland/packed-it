package com.packedit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shipwreck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String description;
    String condition;
    Integer depth;
    Double latitude;
    Double longitude;
    Integer yearDiscovered;

    public Shipwreck() {
    }

    public Shipwreck(final Long id, final String name, final String description, final String condition, final Integer depth, final Double latitude,
            final Double longitude, final Integer yearDiscovered) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.depth = depth;
        this.latitude = latitude;
        this.longitude = longitude;
        this.yearDiscovered = yearDiscovered;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(final Integer depth) {
        this.depth = depth;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(final Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(final Double longitude) {
        this.longitude = longitude;
    }

    public Integer getYearDiscovered() {
        return yearDiscovered;
    }

    public void setYearDiscovered(final Integer yearDiscovered) {
        this.yearDiscovered = yearDiscovered;
    }
}
