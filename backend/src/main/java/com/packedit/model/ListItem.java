package com.packedit.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ListItem extends BaseEntity {

    @ManyToOne
    private PackingList list;

    @ManyToOne
    private Item item;

    public PackingList getList() {
        return list;
    }

    public void setList(final PackingList list) {
        this.list = list;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(final Item item) {
        this.item = item;
    }
}
