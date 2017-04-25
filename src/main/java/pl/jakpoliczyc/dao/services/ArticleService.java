package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.web.dto.CommentDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import java.util.List;

public interface ArticleService {
    void save(StoryMenuTagDto wrapper);
    void update(long articleId, StoryMenuTagDto wrapper);
    void save(long articleId, CommentDto wrapper);
    void removeComment(long articleId, long commentId);
    Article find(long id);
    List<Article> findAll();
}
