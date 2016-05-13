package com.packedit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packedit.model.Shipwreck;

public interface ShipwreckRepository extends JpaRepository<Shipwreck, Long> {

}
