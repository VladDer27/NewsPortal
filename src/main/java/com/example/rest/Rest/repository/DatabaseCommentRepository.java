package com.example.rest.Rest.repository;

import com.example.rest.Rest.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseCommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByNews_Id(Long newsId);

}
