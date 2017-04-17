package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> findAll();
    void remove(long id);
}
