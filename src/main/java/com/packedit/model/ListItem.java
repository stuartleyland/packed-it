package com.packedit.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ListItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ListId")
    private PackingList list;

    @ManyToOne
    @JoinColumn(name = "ItemId")
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
