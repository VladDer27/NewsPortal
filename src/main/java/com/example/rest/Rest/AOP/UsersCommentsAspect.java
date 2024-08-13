package com.example.rest.Rest.AOP;


import com.example.rest.Rest.model.Comment;
import com.example.rest.Rest.model.User;
import com.example.rest.Rest.service.CommentService;
import com.example.rest.Rest.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class UsersCommentsAspect {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Before("@annotation(com.example.rest.Rest.AOP.CheckUsersComments) && args(id, ..)")
    public void checkAccessToUsersComments(JoinPoint joinPoint, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByEmail(authentication.getName());
        Comment currentComment = commentService.findById(id);
        if (Objects.equals(currentUser.getRole().toString(), "ROLE_USER") && !Objects.equals(currentComment.getUser().getId(), currentUser.getId())) {
            throw new SecurityException("У вас нет доступа к данному комментарию!");
        }
    }
}