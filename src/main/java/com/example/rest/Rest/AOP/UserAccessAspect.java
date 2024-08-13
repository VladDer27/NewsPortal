package com.example.rest.Rest.AOP;

import com.example.rest.Rest.model.User;
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
public class UserAccessAspect {

    @Autowired
    private UserService userService;


    @Before("@annotation(com.example.rest.Rest.AOP.CheckUserAccess) && args(id, ..)")
    public void checkAccessToFindById(JoinPoint joinPoint, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByEmail(authentication.getName());
        if (Objects.equals(currentUser.getRole().toString(), "ROLE_USER") && !Objects.equals(currentUser.getId(), id)) {
            throw new SecurityException("У вас нет доступа к данному пользователю!");
        }
    }
}
