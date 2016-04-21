package com.packedit.service.test;

import static com.packedit.util.matcher.ListItemMatcher.listItemsAreLinkedToItems;
import static com.packedit.util.matcher.PackingListMatcher.editableListFieldsMatch;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packedit.Application;
import com.packedit.model.Item;
import com.packedit.model.ItemCategory;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;
import com.packedit.service.ListManager;
import com.packedit.util.TestUtils;
import com.packedit.util.builder.ItemCategoryBuilder;
import com.packedit.util.builder.ItemsBuilder;
import com.packedit.util.builder.PackingListBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ListManagerTest {

    private static final Date NOW = new Date();
    private static final Date TOMORROW = populateTomorrowDate();
    private static final Date TWO_DAYS = populateTwoDaysDate();

    private static Date populateTomorrowDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(NOW);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    private static Date populateTwoDaysDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(NOW);
        calendar.set(Calendar.DATE, 2);
        return calendar.getTime();
    }

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private ListManager listManager;

    @Test
    public void listIsCreatedWithNullStartAndEndDates() {
        final PackingList listToCreate = new PackingListBuilder().withDescription(TestUtils.LIST_DESCRIPTION).build();
        final PackingList list = listManager.createOrUpdateList(listToCreate);
        assertThat("List should have been created", list, editableListFieldsMatch(listToCreate));
    }

    @Test
    public void listIsCreatedWithNullEndDate() {
        final PackingList listToCreate = new PackingListBuilder()
                .withDescription(TestUtils.LIST_DESCRIPTION)
                .withStartDate(NOW)
                .build();
        final PackingList list = listManager.createOrUpdateList(listToCreate);

        assertThat("List should have been created", list, editableListFieldsMatch(listToCreate));
    }

    @Test
    public void listIsCreatedWithAllFieldsSpecified() {
        final PackingList listToCreate = new PackingListBuilder()
                .withDescription(TestUtils.LIST_DESCRIPTION)
                .withStartDate(NOW)
                .withEndDate(TOMORROW)
                .build();
        final PackingList list = listManager.createOrUpdateList(listToCreate);

        assertThat("List should have been created", list, editableListFieldsMatch(listToCreate));
    }

    @Test
    public void descriptionIsUpdated() {
        final PackingList listToCreate = new PackingListBuilder().withDescription(TestUtils.LIST_DESCRIPTION).build();
        final PackingList list = listManager.createOrUpdateList(listToCreate);
        final String updatedDescription = "Updated description";
        list.setDescription(updatedDescription);
        final PackingList updatedList = listManager.createOrUpdateList(list);

        assertThat("Description should have been updated", list, editableListFieldsMatch(updatedList));
    }

    @Test
    public void startDateIsUpdated() {
        final PackingList listToCreate = new PackingListBuilder()
                .withDescription(TestUtils.LIST_DESCRIPTION)
                .withStartDate(NOW)
                .build();
        final PackingList list = listManager.createOrUpdateList(listToCreate);
        list.setStartDate(TOMORROW);
        final PackingList updatedList = listManager.createOrUpdateList(list);

        assertThat("Start date should have been updated", list, editableListFieldsMatch(updatedList));
    }

    @Test
    public void descriptionFieldIsUpdated() {
        final PackingList listToCreate = new PackingListBuilder()
                .withDescription(TestUtils.LIST_DESCRIPTION)
                .withStartDate(NOW)
                .withEndDate(TOMORROW)
                .build();
        final PackingList list = listManager.createOrUpdateList(listToCreate);
        list.setEndDate(TWO_DAYS);
        final PackingList updatedList = listManager.createOrUpdateList(list);

        assertThat("End date should have been updated", list, editableListFieldsMatch(updatedList));
    }

    @Test
    public void fullyLoadedListIsReturned() {
        ItemCategory category = new ItemCategoryBuilder().build();
        category = testUtils.saveCategory(category);

        List<Item> items = new ItemsBuilder().withXItemsInCategory(2, category).build();
        items = testUtils.saveItems(items);

        PackingList createdList = new PackingListBuilder().withItems(items).build();
        createdList = testUtils.saveList(createdList);

        final PackingList retrievedList = listManager.retrieveFullyLoadedList(createdList);
        assertThat("The list items should be linked to the items", retrievedList.getItems(), listItemsAreLinkedToItems(items));
    }

    @Test
    public void itemsAreAddedToList() {
        final PackingList list = new PackingListBuilder().build();

        ItemCategory category = new ItemCategoryBuilder().build();
        category = testUtils.saveCategory(category);
        List<Item> items = new ItemsBuilder().withXItemsInCategory(3, category).build();
        items = testUtils.saveItems(items);

        final PackingList updatedList = listManager.addItemsToList(list, items);

        final List<ListItem> listItems = listManager.getListItems(updatedList);
        assertThat("The list should have three items", listItems.size(), equalTo(3));
        assertThat("List items should be linked to the items", listItems, listItemsAreLinkedToItems(items));
    }

    @Test
    public void listItemsCanBeRetrievedForAList() {
        ItemCategory category = new ItemCategoryBuilder().build();
        category = testUtils.saveCategory(category);

        List<Item> items = new ItemsBuilder().withXItemsInCategory(2, category).build();
        items = testUtils.saveItems(items);

        PackingList createdList = new PackingListBuilder().withItems(items).build();
        createdList = testUtils.saveList(createdList);

        final List<ListItem> listItems = listManager.getListItems(createdList);
        assertThat("List items should match the items the list was created with", listItems, listItemsAreLinkedToItems(items));
    }
}
