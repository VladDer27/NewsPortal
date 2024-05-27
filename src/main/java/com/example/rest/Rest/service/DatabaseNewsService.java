package com.example.rest.Rest.service;

import com.example.rest.Rest.exception.AlreadyExistException;
import com.example.rest.Rest.exception.EntityNotFoundException;
import com.example.rest.Rest.model.Category;
import com.example.rest.Rest.model.News;
import com.example.rest.Rest.model.User;
import com.example.rest.Rest.repository.DatabaseNewsRepository;
import com.example.rest.Rest.repository.NewsSpecification;
import com.example.rest.Rest.utils.BeanUtils;
import com.example.rest.Rest.web.model.news.NewsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseNewsService implements NewsService {

    private final DatabaseNewsRepository newsRepository;

    private final UserService userService;

    private final CategoryService categoryService;


    @Override
    public List<News> findAll(NewsFilter filter) {
        return newsRepository.findAll(NewsSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.
                format("Новость с ID: {0} не найдена!", id)));
    }

    @Override
    public News save(News news) {
        if(newsRepository.existsNewsByNewsBody(news.getNewsBody())){
            throw new AlreadyExistException("Такая новость уже существует!");
        }
        User user = userService.findById(news.getUser().getId());
        Category category = categoryService.findById(news.getCategory().getId());
        news.setUser(user);
        news.setCategory(category);
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        if(newsRepository.existsNewsByNewsBody(news.getNewsBody())){
            throw new AlreadyExistException("Такая новость уже существует!");
        }
        User user = userService.findById(news.getUser().getId());
        Category category = categoryService.findById(news.getCategory().getId());
        News existedNews = findById(news.getId());

        BeanUtils.copyNonNullProperties(news, existedNews);
        existedNews.setCategory(category);
        existedNews.setUser(user);
        return newsRepository.save(existedNews);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
