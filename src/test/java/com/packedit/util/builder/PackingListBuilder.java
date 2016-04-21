package com.packedit.util.builder;

import java.util.Date;
import java.util.List;

import com.packedit.model.Item;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;

public class PackingListBuilder {

    private final PackingList list = new PackingList();

    private String description = "A Packing List";
    private Date startDate = null;
    private Date endDate = null;
    private List<ListItem> items = new ListItemsBuilder(list).build();

    public PackingListBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    public PackingListBuilder withStartDate(final Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public PackingListBuilder withEndDate(final Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public PackingListBuilder withXItems(final int numberOfItems) {
        this.items = new ListItemsBuilder(list).withXItems(numberOfItems).build();
        return this;
    }

    public PackingListBuilder withItemNames(final List<String> itemNames) {
        this.items = new ListItemsBuilder(list).withItemNames(itemNames).build();
        return this;
    }

    public PackingListBuilder withItems(final List<Item> items) {
        this.items = new ListItemsBuilder(list).withItems(items).build();
        return this;
    }

    public PackingList build() {
        list.setDescription(description);
        list.setStartDate(startDate);
        list.setEndDate(endDate);
        list.setItems(items);
        return list;
    }
}
