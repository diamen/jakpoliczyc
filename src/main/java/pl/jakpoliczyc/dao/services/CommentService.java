package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
}
