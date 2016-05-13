package com.packedit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.packedit.model.PackingList;

@Repository
public interface PackingListRepository extends JpaRepository<PackingList, Long> {

    @Query("SELECT l FROM PackingList l JOIN FETCH l.items WHERE l.id = (:id)")
    public PackingList findByIdAndLoadFully(@Param("id") Long id);
}
