package com.example.rest.Rest.service;

import com.example.rest.Rest.model.News;
import com.example.rest.Rest.web.model.PaginationRequest;

import java.util.List;

public interface NewsService {
    List<News> findAll(PaginationRequest request);

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);
}
