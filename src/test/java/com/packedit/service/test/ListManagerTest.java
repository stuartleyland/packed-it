package com.packedit.service.test;

import static com.packedit.util.matcher.ListItemMatcher.listItemsAreLinkedToItems;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packedit.Application;
import com.packedit.model.Item;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;
import com.packedit.service.ListManager;
import com.packedit.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ListManagerTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private ListManager listManager;

    @Test
    public void itemsAreAddedToList() {
        final PackingList list = testUtils.createMinimalListSaved();
        final Item item1 = testUtils.createMinimalItemSaved();
        final Item item2 = testUtils.createMinimalItemSaved();
        final Item item3 = testUtils.createMinimalItemSaved();

        final PackingList updatedList = listManager.addItemsToList(list, item1, item2, item3);

        final List<ListItem> listItems = listManager.getListItems(updatedList);
        assertThat("The list should have three items", listItems.size(), equalTo(3));
        assertThat("List items should be linked to the items", listItems, listItemsAreLinkedToItems(item1, item2, item3));
    }

    @Test
    public void listItemsCanBeRetrievedForAList() {
        final Item item1 = testUtils.createMinimalItemSaved();
        final Item item2 = testUtils.createMinimalItemSaved();
        final PackingList createdList = testUtils.createListWithItems(item1, item2);

        final List<ListItem> listItems = listManager.getListItems(createdList);
        assertThat("List items should match the items the list was created with", listItems, listItemsAreLinkedToItems(item1, item2));
    }
}
