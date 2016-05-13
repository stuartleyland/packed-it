package com.packedit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packedit.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
