package com.packedit.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item extends BaseEntity {

    private String description;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private ItemCategory category;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(final ItemCategory category) {
        this.category = category;
    }
}
