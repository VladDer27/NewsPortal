package com.example.rest.Rest.repository;

import com.example.rest.Rest.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseNewsRepository extends JpaRepository<News, Long> {
}