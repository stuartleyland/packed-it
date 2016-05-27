package com.packedit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PackingList extends BaseEntity {

    @NotNull
    @ManyToOne
    @JsonBackReference
    private User user;

    @NotNull(message = "Description is required")
    @Size(max = 200, message = "Description cannot be more than 200 characters")
    private String description;

    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
    private List<ListItem> items = new ArrayList<>();

    public void addListItem(final ListItem listItem) {
        items.add(listItem);
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

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
