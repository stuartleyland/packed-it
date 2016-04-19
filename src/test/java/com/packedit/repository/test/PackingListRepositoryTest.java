package com.packedit.repository.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.packedit.Application;
import com.packedit.model.PackingList;
import com.packedit.repository.PackingListRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class PackingListRepositoryTest {

    @Autowired
    private PackingListRepository packingListRepository;

    @Test
    public void newListCanBeCreatedAndRetrieved() {
        final PackingList list = new PackingList();
        list.setDescription("Valencia");

        final PackingList savedList = packingListRepository.saveAndFlush(list);
        final PackingList retrievedList = packingListRepository.findOne(savedList.getId());

        assertThat("Retrieved list should be the same as the saved list", retrievedList, equalTo(savedList));
    }
}
