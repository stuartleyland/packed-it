package com.packedit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packedit.model.Authority;
import com.packedit.model.AuthorityName;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    public Authority findByName(AuthorityName name);
}
