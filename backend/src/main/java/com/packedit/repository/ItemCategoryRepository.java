package com.packedit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packedit.model.ItemCategory;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

}
