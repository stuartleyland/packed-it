package com.packedit.util.builder;

import java.util.ArrayList;
import java.util.List;

import com.packedit.model.Item;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;

public class ListItemsBuilder {

    private final List<ListItem> listItems = new ArrayList<>();
    private final PackingList list;

    public ListItemsBuilder(final PackingList list) {
        this.list = list;
    }

    public ListItemsBuilder withItems(final List<Item> items) {
        items.forEach(item -> listItems.add(new ListItemBuilder(list, item).build()));
        return this;
    }

    public List<ListItem> build() {
        return listItems;
    }
}
