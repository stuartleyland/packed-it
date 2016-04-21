package com.packedit.util.builder;

import java.util.ArrayList;
import java.util.List;

import com.packedit.model.Item;
import com.packedit.model.ItemCategory;

public class ItemsBuilder {

    private final List<Item> items = new ArrayList<>();

    public ItemsBuilder withXItemsInCategory(final int numberOfItems, final ItemCategory category) {
        for (int i = 0; i < numberOfItems; i++) {
            items.add(new ItemBuilder().withCategory(category).build());
        }

        return this;
    }

    public List<Item> build() {
        return items;
    }
}
