package com.sample.talha.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.talha.entities.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
   Users findByName(String username);
}