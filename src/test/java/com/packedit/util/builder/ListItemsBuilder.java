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

    public ListItemsBuilder withXItems(final int numberOfItems) {
        for (int i = 0; i < numberOfItems; i++) {
            listItems.add(new ListItemBuilder(list).build());
        }
        return this;
    }

    public ListItemsBuilder withItemNames(final List<String> itemNames) {
        for (final String itemName : itemNames) {
            final Item item = new ItemBuilder().withDescription(itemName).build();
            listItems.add(new ListItemBuilder(list).forItem(item).build());
        }
        return this;
    }

    public ListItemsBuilder withItems(final List<Item> items) {
        items.forEach(item -> listItems.add(new ListItemBuilder(list).forItem(item).build()));
        return this;
    }

    public List<ListItem> build() {
        return listItems;
    }
}
