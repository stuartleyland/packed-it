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
import com.packedit.model.ItemCategory;
import com.packedit.repository.ItemCategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ItemCategoryRepositoryTest {

    private static final String LIST_DESCRIPTION = "Test List";

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void exceptionIsThrownIfDescriptionIsNotSet() {
        exception.expect(DataIntegrityViolationException.class);

        final ItemCategory category = new ItemCategory();
        itemCategoryRepository.saveAndFlush(category);
    }

    @Test
    public void newCategoryCanBeCreatedAndRetrieved() {
        final ItemCategory category = new ItemCategory();
        category.setDescription(LIST_DESCRIPTION);

        final ItemCategory savedCategory = itemCategoryRepository.saveAndFlush(category);
        final ItemCategory retrievedCategory = itemCategoryRepository.findOne(savedCategory.getId());

        assertThat("Retrieved category should be the same as the saved category", retrievedCategory, equalTo(savedCategory));
    }

    @Test
    public void listIsNotEqualAfterUpdate() {
        final ItemCategory category = new ItemCategory();
        category.setDescription(LIST_DESCRIPTION);

        final ItemCategory savedCategory = itemCategoryRepository.saveAndFlush(category);
        final ItemCategory retrievedCategory = itemCategoryRepository.findOne(savedCategory.getId());
        retrievedCategory.setDescription("Updated description");

        final ItemCategory updatedCategory = itemCategoryRepository.saveAndFlush(retrievedCategory);

        assertThat("Categories should not be equal after update due to increased version number", updatedCategory, not(equalTo(retrievedCategory)));
        assertThat("ID should be the same", updatedCategory.getId(), equalTo(retrievedCategory.getId()));
        assertThat("Version number should have increased", updatedCategory.getVersion(), greaterThan(retrievedCategory.getVersion()));
    }
}
