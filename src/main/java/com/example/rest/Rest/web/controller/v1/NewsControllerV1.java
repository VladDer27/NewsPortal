package com.example.rest.Rest.web.controller.v1;

import com.example.rest.Rest.mapper.NewsMapper;
import com.example.rest.Rest.model.News;
import com.example.rest.Rest.service.NewsService;
import com.example.rest.Rest.web.model.news.NewsFilter;
import com.example.rest.Rest.web.model.news.NewsListResponse;
import com.example.rest.Rest.web.model.news.NewsResponse;
import com.example.rest.Rest.web.model.news.UpsertNewsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/news")
public class NewsControllerV1 {

    private final NewsService newsService;
    private final NewsMapper mapper;

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter filter){
        return ResponseEntity.ok(mapper.newsListToResponse(newsService.findAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(mapper.newsToResponse(newsService.findById(id)));
    }

    @PostMapping("/create")
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request){
        News newNews = newsService.save(mapper.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.newsToResponse(newNews));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @RequestBody UpsertNewsRequest request){
        News updatedNews = newsService.update(mapper.requestToNewsWithId(id, request));

        return ResponseEntity.ok().body(mapper.newsToResponse(updatedNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
