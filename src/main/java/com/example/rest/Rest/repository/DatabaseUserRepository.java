package com.example.rest.Rest.repository;

import com.example.rest.Rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatabaseUserRepository extends JpaRepository<User, Long> {

    boolean existsUserByEmail(String email);

    boolean existsUserByNickname(String nickname);

    Optional<User> findByEmail(String email);
}
