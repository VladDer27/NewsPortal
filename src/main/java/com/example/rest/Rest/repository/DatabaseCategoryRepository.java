package com.example.rest.Rest.repository;

import com.example.rest.Rest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseCategoryRepository extends JpaRepository<Category, Long> {

}
