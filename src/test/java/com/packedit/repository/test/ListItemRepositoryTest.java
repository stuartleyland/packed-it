package com.packedit.repository.test;

import static com.packedit.util.matcher.ListItemMatcher.listItemsAreLinkedToItems;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packedit.Application;
import com.packedit.model.Item;
import com.packedit.model.ItemCategory;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;
import com.packedit.repository.ListItemRepository;
import com.packedit.service.ListManager;
import com.packedit.util.TestUtils;
import com.packedit.util.builder.ItemBuilder;
import com.packedit.util.builder.ItemCategoryBuilder;
import com.packedit.util.builder.ItemsBuilder;
import com.packedit.util.builder.ListItemBuilder;
import com.packedit.util.builder.PackingListBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ListItemRepositoryTest {

    @Autowired
    private ListItemRepository listItemRepository;

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private ListManager listManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void exceptionIsThrownIfListIsNotSet() {
        final ListItem listItem = new ListItem();
        listItem.setItem(testUtils.createMinimalItemSaved());

        exception.expect(DataIntegrityViolationException.class);
        listItemRepository.saveAndFlush(listItem);
    }

    @Test
    public void exceptionIsThrownIfItemIsNotSet() {
        final ListItem listItem = new ListItem();
        listItem.setList(testUtils.createMinimalListUnsaved());

        exception.expect(DataIntegrityViolationException.class);
        listItemRepository.saveAndFlush(listItem);
    }

    @Test
    public void listItemCanBeCreatedAndRetrieved() {
        PackingList list = new PackingListBuilder().build();
        list = testUtils.saveList(list);

        ItemCategory category = new ItemCategoryBuilder().build();
        category = testUtils.saveCategory(category);

        Item item = new ItemBuilder(category).build();
        item = testUtils.saveItem(item);

        final ListItem listItem = new ListItemBuilder(list, item).build();
        final ListItem savedListItem = listItemRepository.saveAndFlush(listItem);
        final ListItem retrievedListItem = listItemRepository.findOne(savedListItem.getId());

        assertThat("Saved and retrieved list items should be the same", retrievedListItem, equalTo(savedListItem));
    }

    @Test
    public void listItemsCanBeRetrievedForAList() {
        ItemCategory category = new ItemCategoryBuilder().build();
        category = testUtils.saveCategory(category);

        List<Item> items = new ItemsBuilder().withXItemsInCategory(2, category).build();
        items = testUtils.saveItems(items);

        PackingList createdList = new PackingListBuilder().withItems(items).build();
        createdList = listManager.createOrUpdateList(createdList);

        final List<ListItem> listItems = listItemRepository.findByList(createdList);
        assertThat("List items should match the items the list was created with", listItems, listItemsAreLinkedToItems(items));
    }
}
