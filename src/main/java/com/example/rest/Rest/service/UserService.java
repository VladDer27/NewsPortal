package com.example.rest.Rest.service;

import com.example.rest.Rest.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

}
