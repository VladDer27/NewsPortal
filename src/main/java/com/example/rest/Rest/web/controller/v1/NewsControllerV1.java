package com.example.rest.Rest.web.controller.v1;

import com.example.rest.Rest.AOP.CheckNewsOwnership;
import com.example.rest.Rest.mapper.NewsMapper;
import com.example.rest.Rest.model.News;
import com.example.rest.Rest.security.UserPrincipal;
import com.example.rest.Rest.service.NewsService;
import com.example.rest.Rest.service.UserService;
import com.example.rest.Rest.web.model.news.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/news")
public class NewsControllerV1 {

    private final NewsService newsService;
    private final NewsMapper mapper;

    private final UserService userService;
    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter filter){
        return ResponseEntity.ok(mapper.newsListToResponse(newsService.findAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsByIdResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.newsByIdToResponse(newsService.findById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request,
                                               @AuthenticationPrincipal UserPrincipal principal){
        News newNews = mapper.requestToNews(request);
        newNews.setUser(userService.findById(principal.getUserId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.newsToResponse(newsService.save(newNews)));
    }

    @PutMapping("/{id}")
    @CheckNewsOwnership
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @RequestBody UpsertNewsRequest request,
                                               @AuthenticationPrincipal UserPrincipal principal){
        News updatedNews = mapper.requestToNews(request);
        updatedNews.setUser(userService.findById(principal.getUserId()));

        return ResponseEntity.ok().body(mapper.newsToResponse(
                newsService.update(mapper.requestToNewsWithId(id, request))));
    }

    @DeleteMapping("/{id}")
    @CheckNewsOwnership
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
