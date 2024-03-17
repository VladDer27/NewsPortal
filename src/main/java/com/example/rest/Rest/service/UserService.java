package com.example.rest.Rest.service;

import com.example.rest.Rest.model.User;
import com.example.rest.Rest.web.model.PaginationRequest;

import java.util.List;

public interface UserService {

    List<User> findAll(PaginationRequest request);

    User findById(Long id);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

}
