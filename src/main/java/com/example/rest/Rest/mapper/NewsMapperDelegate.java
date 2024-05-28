package com.example.rest.Rest.mapper;

import com.example.rest.Rest.model.News;
import com.example.rest.Rest.service.CategoryService;
import com.example.rest.Rest.service.UserService;
import com.example.rest.Rest.web.model.news.NewsResponse;
import com.example.rest.Rest.web.model.news.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NewsMapperDelegate implements NewsMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        news.setTitle(request.getTitle());
        news.setNewsBody(request.getNewsBody());
        news.setCategory(categoryService.findById(request.getCategoryId()));
        return news;
    }

    @Override
    public News requestToNewsWithId(Long newsId, UpsertNewsRequest request) {

        News news = requestToNews(request);
        news.setId(newsId);

        return news;
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse response = new NewsResponse();
        response.setCategoryName(news.getCategory().getName());
        response.setUserNickname(news.getUser().getNickname());
        response.setId(news.getId());
        response.setTitle(news.getTitle());
        response.setNewsBody(news.getNewsBody());
        response.setCreatedAt(news.getCreatedAt());
        response.setUpdatedAt(news.getUpdatedAt());
        return response;
    }
}
