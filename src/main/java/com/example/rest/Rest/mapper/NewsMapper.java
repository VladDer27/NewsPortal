package com.example.rest.Rest.mapper;

import com.example.rest.Rest.model.Comment;
import com.example.rest.Rest.model.News;
import com.example.rest.Rest.web.model.comment.CommentResponse;
import com.example.rest.Rest.web.model.news.NewsByIdResponse;
import com.example.rest.Rest.web.model.news.NewsListResponse;
import com.example.rest.Rest.web.model.news.NewsResponse;
import com.example.rest.Rest.web.model.news.UpsertNewsRequest;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface NewsMapper {
    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNewsWithId(Long newsId, UpsertNewsRequest request);

    NewsResponse newsToResponse(News news);

    NewsByIdResponse newsByIdToResponse(News news);

    CommentResponse commentToResponse(Comment comment);

    default List<CommentResponse> commentListToResponse(List<Comment> comments){

        return comments.stream().map(this::commentToResponse).collect(Collectors.toList());
    }

    default NewsListResponse newsListToResponse(List<News> news){
        NewsListResponse response = new NewsListResponse();

        response.setNews(news.stream().map(this::newsToResponse).collect(Collectors.toList()));

        return response;
    }
}
