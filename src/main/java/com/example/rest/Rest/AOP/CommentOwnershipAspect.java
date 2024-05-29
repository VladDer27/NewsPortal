package com.example.rest.Rest.AOP;

import com.example.rest.Rest.model.Comment;
import com.example.rest.Rest.service.CommentService;
import com.example.rest.Rest.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommentOwnershipAspect {

    @Autowired
    private CommentService commentService;


    @Before("@annotation(com.example.rest.Rest.AOP.CheckCommentOwnership) && args(id,..)")
    public void checkOwnership(JoinPoint joinPoint, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Comment comment = commentService.findById(id);

        if (!comment.getUser().getEmail().equals(currentUsername)) {
            throw new SecurityException("У вас нет доступа, чтобы изменить этот комментарий!");
        }
    }
}
