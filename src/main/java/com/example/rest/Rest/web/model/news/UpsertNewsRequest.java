package com.example.rest.Rest.web.model.news;

import lombok.Data;

@Data
public class UpsertNewsRequest {

    private String title;

    private String newsBody;

    private Long userId;

    private Long categoryId;
}
