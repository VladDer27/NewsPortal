package com.example.rest.Rest.mapper;

import com.example.rest.Rest.model.Comment;
import com.example.rest.Rest.web.model.comment.CommentListResponse;
import com.example.rest.Rest.web.model.comment.CommentResponse;
import com.example.rest.Rest.web.model.comment.UpsertCommentRequest;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CommentMapper {
    Comment requestToComment (UpsertCommentRequest request);

    @Mapping(source = "commentId", target = "id")
    Comment requestToCommentWithId(Long commentId, UpsertCommentRequest request);

    CommentResponse commentToResponse(Comment comment);

    default CommentListResponse commentListToResponse(List<Comment> comments){
        CommentListResponse response = new CommentListResponse();

        response.setComments(comments.stream().map(this::commentToResponse).collect(Collectors.toList()));

        return response;
    }
}
