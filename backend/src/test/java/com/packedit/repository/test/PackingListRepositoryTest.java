package com.packedit.repository.test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packedit.Application;
import com.packedit.model.PackingList;
import com.packedit.model.User;
import com.packedit.repository.PackingListRepository;
import com.packedit.util.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PackingListRepositoryTest {

    private static final String LIST_DESCRIPTION = "Test List";

    @Autowired
    private PackingListRepository packingListRepository;

    @Autowired
    private TestUtils testUtils;

    private User testUser;

    @Before
    public void createTestUser() {
        testUser = testUtils.getOrCreateTestUser();
    }

    @Test
    public void exceptionIsThrownIfDescriptionIsNotSet() {
        final PackingList list = new PackingList();
        list.setUser(testUser);

        try {
            packingListRepository.saveAndFlush(list);
        } catch (final Exception exception) {
            assertThat("Exception should be of type ConstraintViolationException", exception, instanceOf(ConstraintViolationException.class));

            final ConstraintViolationException violationException = (ConstraintViolationException)exception;
            final Set<ConstraintViolation<?>> violations = violationException.getConstraintViolations();

            assertThat("Only one constraint violation should have occurred", violations.size(), equalTo(1));

            final ConstraintViolation<?> violation = violations.stream().findFirst().get();
            assertThat("Description is mandatory", violation.getMessage(), equalTo("Description is required"));
        }
    }

    @Test
    public void exceptionIsThrownIfDescriptionIsTooLong() {
        final PackingList list = new PackingList();
        list.setUser(testUser);
        list.setDescription(StringUtils.repeat("a", 201));
        try {
            packingListRepository.saveAndFlush(list);
        } catch (final Exception exception) {
            assertThat("Exception should be of type ConstraintViolationException", exception, instanceOf(ConstraintViolationException.class));

            final ConstraintViolationException violationException = (ConstraintViolationException)exception;
            final Set<ConstraintViolation<?>> violations = violationException.getConstraintViolations();

            assertThat("Only one constraint violation should have occurred", violations.size(), equalTo(1));

            final ConstraintViolation<?> violation = violations.stream().findFirst().get();
            assertThat("Description is mandatory", violation.getMessage(), equalTo("Description cannot be more than 200 characters"));
        }
    }

    @Test
    public void newListCanBeCreatedAndRetrieved() {
        final PackingList list = new PackingList();
        list.setUser(testUser);
        list.setDescription(LIST_DESCRIPTION);

        final PackingList savedList = packingListRepository.saveAndFlush(list);
        final PackingList retrievedList = packingListRepository.findOne(savedList.getId());

        assertThat("Retrieved list should be the same as the saved list", retrievedList, equalTo(savedList));
    }

    @Test
    public void listIsNotEqualAfterUpdate() {
        final PackingList list = new PackingList();
        list.setUser(testUser);
        list.setDescription(LIST_DESCRIPTION);

        final PackingList savedList = packingListRepository.saveAndFlush(list);
        final PackingList retrievedList = packingListRepository.findOne(savedList.getId());

        retrievedList.setStartDate(new Date());
        final PackingList updatedList = packingListRepository.saveAndFlush(retrievedList);

        assertThat("Lists should not be equal after update due to increased version number", updatedList, not(equalTo(retrievedList)));
        assertThat("ID should be the same", updatedList.getId(), equalTo(retrievedList.getId()));
        assertThat("Version number should have increased", updatedList.getVersion(), greaterThan(retrievedList.getVersion()));
    }
}
