package com.example.rest.Rest.repository;

import com.example.rest.Rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseUserRepository extends JpaRepository<User, Long> {
}
