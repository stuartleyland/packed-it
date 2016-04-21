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
public class ListManager {

    @Autowired
    private PackingListRepository listRepository;

    @Autowired
    private ListItemRepository listItemRepository;

    @Transactional
    public PackingList addItemsToList(final PackingList list, final Item... items) {
        for (final Item item : items) {
            final ListItem listItem = new ListItem();
            listItem.setList(list);
            listItem.setItem(item);
            listItemRepository.save(listItem);

            list.addListItem(listItem);
        }

        return listRepository.save(list);
    }

    @Transactional
    public List<ListItem> getListItems(final PackingList list) {
        return listItemRepository.findByList(list);
    }
}
