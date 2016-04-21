package com.packedit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packedit.model.Item;
import com.packedit.model.ItemCategory;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;
import com.packedit.repository.ItemCategoryRepository;
import com.packedit.repository.ItemRepository;
import com.packedit.repository.ListItemRepository;
import com.packedit.repository.PackingListRepository;
import com.packedit.service.ListManager;

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

    @Autowired
    private ListItemRepository listItemRepository;

    @Autowired
    private ListManager listManager;

    public PackingList createMinimalListUnsaved() {
        return createMinimalList(false);
    }

    public PackingList createMinimalListSaved() {
        return createMinimalList(true);
    }

    private PackingList createMinimalList(final boolean save) {
        PackingList list = new PackingList();
        list.setDescription(LIST_DESCRIPTION);
        if (save) {
            list = listRepository.saveAndFlush(list);
        }
        return list;
    }

    public Item createMinimalItemUnsaved() {
        return createMinimalItem(false);
    }

    public Item createMinimalItemSaved() {
        return createMinimalItem(true);
    }

    private Item createMinimalItem(final boolean save) {
        ItemCategory category = new ItemCategory();
        category.setDescription(CATEGORY_DESCRIPTION);
        category = categoryRepository.saveAndFlush(category);

        Item item = new Item();
        item.setDescription(ITEM_DESCRIPTION);
        item.setCategory(category);

        if (save) {
            item = itemRepository.saveAndFlush(item);
        }
        return item;
    }

    public ListItem createMinimalListItemUnsaved() {
        return createMinimalListItem(false);
    }

    public ListItem createMinimalListItemSaved() {
        return createMinimalListItem(true);
    }

    private ListItem createMinimalListItem(final boolean save) {
        ListItem listItem = new ListItem();
        listItem.setList(createMinimalListSaved());
        listItem.setItem(createMinimalItemSaved());

        if (save) {
            listItem = listItemRepository.saveAndFlush(listItem);
        }

        return listItem;
    }

    public PackingList createListWithItems(final Item... items) {
        return listManager.addItemsToList(createMinimalListSaved(), items);
    }
}
