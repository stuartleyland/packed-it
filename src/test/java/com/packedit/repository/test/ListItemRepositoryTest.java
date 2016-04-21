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
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;
import com.packedit.repository.ListItemRepository;
import com.packedit.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ListItemRepositoryTest {

    @Autowired
    private ListItemRepository listItemRepository;

    @Autowired
    private TestUtils testUtils;

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
        final ListItem listItem = testUtils.createMinimalListItemUnsaved();
        final ListItem savedListItem = listItemRepository.saveAndFlush(listItem);
        final ListItem retrievedListItem = listItemRepository.findOne(savedListItem.getId());

        assertThat("Saved and retrieved list items should be the same", retrievedListItem, equalTo(savedListItem));
    }

    @Test
    public void listItemsCanBeRetrievedForAList() {
        final Item item1 = testUtils.createMinimalItemSaved();
        final Item item2 = testUtils.createMinimalItemSaved();
        final PackingList createdList = testUtils.createListWithItems(item1, item2);

        final List<ListItem> listItems = listItemRepository.findByList(createdList);
        assertThat("List items should match the items the list was created with", listItems, listItemsAreLinkedToItems(item1, item2));
    }
}
