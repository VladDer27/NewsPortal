package com.example.rest.Rest.web.model.news;

import com.example.rest.Rest.web.model.comment.CommentListResponse;
import com.example.rest.Rest.web.model.comment.CommentResponse;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class NewsByIdResponse {
    private Long id;

    private String title;

    private String newsBody;

    private Instant createdAt;

    private Instant updatedAt;

    private String userNickname;

    private String categoryName;

    private List<CommentResponse> comments = new ArrayList<>();
}
