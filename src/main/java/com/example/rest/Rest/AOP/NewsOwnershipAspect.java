package com.example.rest.Rest.AOP;

import com.example.rest.Rest.model.News;
import com.example.rest.Rest.service.NewsService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NewsOwnershipAspect {

    @Autowired
    private NewsService newsService;

    @Before("@annotation(com.example.rest.Rest.AOP.CheckNewsOwnership) && args(id,..)")
    public void checkOwnership(JoinPoint joinPoint, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        News news = newsService.findById(id);
        if (!news.getUser().getEmail().equals(currentUsername)) {
            throw new SecurityException("У вас нет доступа, чтобы изменить эту новость!");
        }
    }
}
