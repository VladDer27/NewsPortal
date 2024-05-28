package com.example.rest.Rest.web.controller.v1;

import com.example.rest.Rest.mapper.CommentMapper;
import com.example.rest.Rest.model.Comment;
import com.example.rest.Rest.security.UserPrincipal;
import com.example.rest.Rest.service.CommentService;
import com.example.rest.Rest.service.UserService;
import com.example.rest.Rest.web.model.PaginationRequest;
import com.example.rest.Rest.web.model.comment.CommentListResponse;
import com.example.rest.Rest.web.model.comment.CommentResponse;
import com.example.rest.Rest.web.model.comment.UpsertCommentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentControllerV1 {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    private final UserService userService;

    @GetMapping
    public ResponseEntity<CommentListResponse> findAll(@Valid PaginationRequest request){
        return ResponseEntity.ok(commentMapper.commentListToResponse(commentService.findAll(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.findById(id)));
    }
    @PostMapping("/create")
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request,
                                                  @AuthenticationPrincipal UserPrincipal principal){
        Comment newComment = commentMapper.requestToComment(request);
        newComment.setUser(userService.findById(principal.getUserId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper
                .commentToResponse(commentService.save(newComment)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @RequestBody UpsertCommentRequest request,
                                               @AuthenticationPrincipal UserPrincipal principal){
        Comment updatedComment = commentMapper.requestToComment(request);
        updatedComment.setUser(userService.findById(principal.getUserId()));

        return ResponseEntity.ok().body(commentMapper.commentToResponse(
                commentService.update(commentMapper.requestToCommentWithId(id, request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        commentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
