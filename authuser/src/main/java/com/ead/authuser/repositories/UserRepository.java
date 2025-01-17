package com.ead.authuser.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ead.authuser.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);
}
