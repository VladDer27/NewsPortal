package com.example.rest.Rest.mapper;

import com.example.rest.Rest.model.Comment;
import com.example.rest.Rest.service.CategoryService;
import com.example.rest.Rest.service.NewsService;
import com.example.rest.Rest.web.model.comment.CommentResponse;
import com.example.rest.Rest.web.model.comment.UpsertCommentRequest;
import com.example.rest.Rest.web.model.news.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;

abstract public class CommentMapperDelegate implements CommentMapper{

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsService newsService;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setNews(newsService.findById(request.getNewsId()));
        return comment;
    }

    @Override
    public Comment requestToCommentWithId(Long commentId, UpsertCommentRequest request) {

        Comment comment = requestToComment(request);
        comment.setId(commentId);

        return comment;
    }

    @Override
    public CommentResponse commentToResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        response.setUsername(comment.getUser().getNickname());
        response.setId(comment.getId());
        response.setCreatedAt(comment.getCreatedAt());
        response.setUpdatedAt(comment.getUpdatedAt());
        response.setComment(comment.getComment());
        return response;
    }
}
