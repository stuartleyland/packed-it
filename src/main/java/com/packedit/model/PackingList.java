package com.packedit.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class PackingList extends BaseEntity {

    private String description;
    private Date startDate;
    private Date endDate;

    @OneToMany
    private List<ListItem> items;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public List<ListItem> getItems() {
        return items;
    }

    public void setItems(final List<ListItem> items) {
        this.items = items;
    }
}
