package com.packedit.util.builder;

import com.packedit.model.ItemCategory;

public class ItemCategoryBuilder {

    private String description = "A Category";

    public ItemCategoryBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    public ItemCategory build() {
        final ItemCategory category = new ItemCategory();
        category.setDescription(description);
        return category;
    }
}
