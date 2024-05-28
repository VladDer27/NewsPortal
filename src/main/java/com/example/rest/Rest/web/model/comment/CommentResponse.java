package com.example.rest.Rest.web.model.comment;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentResponse {

    private Long id;

    private String comment;

    private String username;

    private Instant createdAt;

    private Instant updatedAt;

}
