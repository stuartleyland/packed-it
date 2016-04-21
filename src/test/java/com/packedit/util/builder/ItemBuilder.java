package com.packedit.util.builder;

import com.packedit.model.Item;
import com.packedit.model.ItemCategory;

public class ItemBuilder {

    private String description = "An Item";
    private ItemCategory category = new ItemCategoryBuilder().build();

    public ItemBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    public ItemBuilder withCategory(final ItemCategory category) {
        this.category = category;
        return this;
    }

    public Item build() {
        final Item item = new Item();
        item.setDescription(description);
        item.setCategory(category);
        return item;
    }
}
