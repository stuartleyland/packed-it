package com.packedit.repository.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packedit.Application;
import com.packedit.model.PackingList;
import com.packedit.repository.PackingListRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PackingListRepositoryTest {

    private static final String LIST_DESCRIPTION = "Test List";

    @Autowired
    private PackingListRepository packingListRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void exceptionIsThrownIfDescriptionIsNotSet() {
        exception.expect(DataIntegrityViolationException.class);

        final PackingList list = new PackingList();
        packingListRepository.saveAndFlush(list);
    }

    @Test
    public void newListCanBeCreatedAndRetrieved() {
        final PackingList list = new PackingList();
        list.setDescription(LIST_DESCRIPTION);

        final PackingList savedList = packingListRepository.saveAndFlush(list);
        final PackingList retrievedList = packingListRepository.findOne(savedList.getId());

        assertThat("Retrieved list should be the same as the saved list", retrievedList, equalTo(savedList));
    }

    @Test
    public void updatingAListUpdatesTheVersionNumber() {
        final PackingList list = new PackingList();
        list.setDescription(LIST_DESCRIPTION);

        final PackingList savedList = packingListRepository.saveAndFlush(list);
        final PackingList retrievedList = packingListRepository.findOne(savedList.getId());

        retrievedList.setStartDate(new Date());
        final PackingList updatedList = packingListRepository.saveAndFlush(retrievedList);

        assertThat("Version number should have increased", updatedList.getVersion(), greaterThan(retrievedList.getVersion()));
    }

    @Test
    public void listIsNotEqualAfterUpdate() {
        final PackingList list = new PackingList();
        list.setDescription(LIST_DESCRIPTION);

        final PackingList savedList = packingListRepository.saveAndFlush(list);
        final PackingList retrievedList = packingListRepository.findOne(savedList.getId());

        retrievedList.setStartDate(new Date());
        final PackingList updatedList = packingListRepository.saveAndFlush(retrievedList);

        assertThat("Lists should not be equal after update due to increased version number", updatedList, not(equalTo(retrievedList)));
        assertThat("ID should be the same", updatedList.getId(), equalTo(savedList.getId()));
        assertThat("Version number should have increased", updatedList.getVersion(), greaterThan(retrievedList.getVersion()));
    }
}
