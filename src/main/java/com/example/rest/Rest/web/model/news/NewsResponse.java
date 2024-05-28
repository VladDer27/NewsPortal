package com.example.rest.Rest.web.model.news;

import lombok.Data;

import java.time.Instant;

@Data
public class NewsResponse {

    private Long id;

    private String title;

    private String newsBody;

    private Instant createdAt;

    private Instant updatedAt;

    private String userNickname;

    private String categoryName;

    private Integer commentsCount;

}
