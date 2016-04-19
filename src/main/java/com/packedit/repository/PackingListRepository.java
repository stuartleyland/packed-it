package com.packedit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.packedit.model.PackingList;

@Repository
public interface PackingListRepository extends JpaRepository<PackingList, Long> {

}
