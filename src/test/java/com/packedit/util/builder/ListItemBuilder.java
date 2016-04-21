package com.packedit.util.builder;

import com.packedit.model.Item;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;

public class ListItemBuilder {

    private final PackingList list;
    private Item item = new ItemBuilder().build();

    public ListItemBuilder(final PackingList list) {
        this.list = list;
    }

    public ListItemBuilder forItem(final Item item) {
        this.item = item;
        return this;
    }

    public ListItem build() {
        final ListItem listItem = new ListItem();
        listItem.setList(list);
        listItem.setItem(item);
        return listItem;
    }
}
