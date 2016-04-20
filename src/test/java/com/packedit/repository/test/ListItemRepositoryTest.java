package com.packedit.repository.test;

import static org.hamcrest.Matchers.equalTo;
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
import com.packedit.model.ListItem;
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
}
