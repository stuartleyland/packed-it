package com.packedit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packedit.model.ListItem;
import com.packedit.model.PackingList;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {

    public List<ListItem> findByList(final PackingList list);
}
