package com.example.rest.Rest.AOP;

import com.example.rest.Rest.model.News;
import com.example.rest.Rest.model.User;
import com.example.rest.Rest.service.NewsService;
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
public class UsersNewsAspect {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Before("@annotation(com.example.rest.Rest.AOP.CheckUsersNews) && args(id, ..)")
    public void checkAccessToUsersNews(JoinPoint joinPoint, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findByEmail(authentication.getName());
        News currentNews = newsService.findById(id);
        if (Objects.equals(currentUser.getRole().toString(), "ROLE_USER") && !Objects.equals(currentNews.getUser().getId(), currentUser.getId())) {
            throw new SecurityException("У вас нет доступа к данной новости!");
        }
    }
}
