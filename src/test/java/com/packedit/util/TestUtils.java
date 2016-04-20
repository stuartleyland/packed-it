package com.packedit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packedit.model.Item;
import com.packedit.model.ItemCategory;
import com.packedit.repository.ItemCategoryRepository;

@Service
public class TestUtils {

    public static final String ITEM_DESCRIPTION = "Item Description";
    public static final String CATEGORY_DESCRIPTION = "Category Description";

    @Autowired
    private ItemCategoryRepository categoryRepository;

    public Item createMinimalItemUnsaved() {
        ItemCategory category = new ItemCategory();
        category.setDescription(CATEGORY_DESCRIPTION);
        category = categoryRepository.saveAndFlush(category);

        final Item item = new Item();
        item.setDescription(ITEM_DESCRIPTION);
        item.setCategory(category);
        return item;
    }
}
