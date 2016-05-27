package com.packedit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packedit.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
