package com.packedit.util.builder;

import com.packedit.model.Item;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;

public class ListItemBuilder {

    private final PackingList list;
    private final Item item;

    public ListItemBuilder(final PackingList list, final Item item) {
        this.list = list;
        this.item = item;
    }

    public ListItem build() {
        final ListItem listItem = new ListItem();
        listItem.setList(list);
        listItem.setItem(item);
        return listItem;
    }
}
