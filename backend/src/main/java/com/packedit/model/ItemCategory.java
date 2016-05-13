package com.packedit.model;

import javax.persistence.Entity;

@Entity
public class ItemCategory extends BaseEntity {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
