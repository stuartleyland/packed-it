package com.packedit.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packedit.model.Item;
import com.packedit.model.ItemCategory;
import com.packedit.model.PackingList;
import com.packedit.repository.ItemCategoryRepository;
import com.packedit.repository.ItemRepository;
import com.packedit.repository.PackingListRepository;

@Service
public class TestUtils {

    public static final String LIST_DESCRIPTION = "List Description";
    public static final String ITEM_DESCRIPTION = "Item Description";
    public static final String CATEGORY_DESCRIPTION = "Category Description";

    @Autowired
    private PackingListRepository listRepository;

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    public ItemCategory saveCategory(final ItemCategory category) {
        return categoryRepository.save(category);
    }

    public Item saveItem(final Item item) {
        return itemRepository.save(item);
    }

    public List<Item> saveItems(final List<Item> items) {
        return itemRepository.save(items);
    }

    public PackingList saveList(final PackingList list) {
        return listRepository.save(list);
    }
}
