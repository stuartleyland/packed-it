package com.packedit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packedit.model.Item;
import com.packedit.model.ListItem;
import com.packedit.model.PackingList;
import com.packedit.repository.ListItemRepository;
import com.packedit.repository.PackingListRepository;

@Service
@Transactional
public class ListManager {

    @Autowired
    private PackingListRepository listRepository;

    @Autowired
    private ListItemRepository listItemRepository;

    public PackingList createOrUpdateList(final PackingList list) {
        return listRepository.save(list);
    }

    public PackingList retrieveFullyLoadedList(final PackingList list) {
        return listRepository.findByIdAndLoadFully(list.getId());
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
