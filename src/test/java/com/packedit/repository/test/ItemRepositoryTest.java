package com.packedit.repository.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

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
import com.packedit.repository.ItemRepository;
import com.packedit.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TestUtils testUtils;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void exceptionIsThrownIfDescriptionIsNotSet() {
        final Item item = new Item();

        exception.expect(DataIntegrityViolationException.class);
        itemRepository.saveAndFlush(item);
    }

    @Test
    public void newItemCanBeCreatedAndRetrieved() {
        final Item item = testUtils.createMinimalItemUnsaved();
        final Item savedItem = itemRepository.saveAndFlush(item);

        final Item retrievedItem = itemRepository.findOne(savedItem.getId());
        assertThat("Retrieved item should be the same as the saved item", retrievedItem, equalTo(savedItem));
    }

    @Test
    public void itemIsNotEqualAfterUpdate() {
        final Item item = testUtils.createMinimalItemUnsaved();
        final Item savedItem = itemRepository.saveAndFlush(item);

        final Item retrievedItem = itemRepository.findOne(savedItem.getId());
        retrievedItem.setDescription("Updated description");
        final Item updatedItem = itemRepository.saveAndFlush(retrievedItem);

        assertThat("Items should not be equal after update due to increased version number", updatedItem, not(equalTo(retrievedItem)));
        assertThat("ID should be the same", updatedItem.getId(), equalTo(retrievedItem.getId()));
        assertThat("Version number should have increased", updatedItem.getVersion(), greaterThan(retrievedItem.getVersion()));
    }
}
