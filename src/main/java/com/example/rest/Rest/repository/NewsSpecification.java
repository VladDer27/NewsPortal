package com.example.rest.Rest.repository;

import com.example.rest.Rest.model.News;
import com.example.rest.Rest.web.model.news.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter newsFilter) {
        return Specification.where(byUserId(newsFilter.getUserId())).
                and(byCategoryId(newsFilter.getCategoryId()));
    }

    static Specification<News> byCategoryId(Long categoryId) {
        return ((root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        });
    }

    static Specification<News> byUserId(Long userId) {
        return ((root, query, criteriaBuilder) -> {
            if (userId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        });
    }


}
