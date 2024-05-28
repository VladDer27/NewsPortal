package com.example.rest.Rest.service;

import com.example.rest.Rest.exception.EntityNotFoundException;
import com.example.rest.Rest.model.Comment;
import com.example.rest.Rest.model.News;
import com.example.rest.Rest.repository.DatabaseCommentRepository;
import com.example.rest.Rest.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseCommentService implements CommentService{

    private final DatabaseCommentRepository commentRepository;

    private final NewsService newsService;

    @Override
    public List<Comment> findAllByNewsId(Long newsId) {
        return commentRepository.findAllByNews_Id(newsId);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.
                format("Комментарий с ID: {0} не найден!", id)));
    }

    @Override
    public Comment save(Comment comment) {
        News news = newsService.findById(comment.getNews().getId());
        comment.setNews(news);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        News news = newsService.findById(comment.getNews().getId());
        Comment existedComment = findById(comment.getId());

        BeanUtils.copyNonNullProperties(comment, existedComment);
        existedComment.setNews(news);
        return commentRepository.save(existedComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
