package com.example.rest.Rest.web.model.news;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsListResponse {
    private List<NewsResponse> news = new ArrayList<>();
}
