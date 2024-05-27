package com.example.rest.Rest.service;

import com.example.rest.Rest.model.News;
import com.example.rest.Rest.web.model.news.NewsFilter;

import java.util.List;

public interface NewsService {

    List<News> findAll(NewsFilter filter);

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);
}
