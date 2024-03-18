package com.example.rest.Rest.service;

import com.example.rest.Rest.model.Category;
import com.example.rest.Rest.web.model.PaginationRequest;

import java.util.List;

public interface CategoryService {

    List<Category> findAll(PaginationRequest request);

    Category findById(Long id);

    Category save(Category category);

    Category update(Category category);

    void deleteById(Long id);
}
