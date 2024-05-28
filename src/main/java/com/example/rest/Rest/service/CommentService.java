package com.example.rest.Rest.service;

import com.example.rest.Rest.model.Comment;
import com.example.rest.Rest.web.model.PaginationRequest;

import java.util.List;

public interface CommentService {

    List<Comment> findAll(PaginationRequest request);

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void deleteById(Long id);
}
