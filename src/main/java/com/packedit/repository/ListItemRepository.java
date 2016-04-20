package com.packedit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packedit.model.ListItem;

public interface ListItemRepository extends JpaRepository<ListItem, Long> {

}
