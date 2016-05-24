package com.packedit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.packedit.model.Item;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;
import com.packedit.repository.ListItemRepository;
import com.packedit.repository.PackingListRepository;

@Service
@Transactional
public class ListManager {

    private static final Logger LOG = LoggerFactory.getLogger(ListManager.class);

    @Autowired
    private PackingListRepository listRepository;

    @Autowired
    private ListItemRepository listItemRepository;

    public PackingList createOrUpdateList(final PackingList list) {
        return listRepository.save(list);
    }

    public List<PackingList> findAll() {
        return listRepository.findAll();
    }

    public List<PackingList> findAllByUser(final UserDetails userDetails) {
        LOG.debug("Retrieving lists for username [{}]", userDetails.getUsername());
        final List<PackingList> lists = listRepository.findAllByUser(userDetails);
        LOG.debug("Finished retrieving lists for username [{}]", userDetails.getUsername());
        return lists;
    }

    public PackingList findById(final long id) {
        return listRepository.findOne(id);
    }

    public PackingList retrieveFullyLoadedList(final PackingList list) {
        return listRepository.findByIdAndLoadFully(list.getId());
    }

    public void deleteList(final PackingList list) {
        listRepository.delete(list);
    }

    public PackingList addItemsToList(final PackingList list, final List<Item> items) {
        for (final Item item : items) {
            final ListItem listItem = new ListItem();
            listItem.setList(list);
            listItem.setItem(item);
            list.addListItem(listItem);
        }

        return listRepository.save(list);
    }

    public List<ListItem> getListItems(final PackingList list) {
        return listItemRepository.findByList(list);
    }
}
