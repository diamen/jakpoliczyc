package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Comment;

import java.util.List;

public interface ArticleRepository {
    List<Article> findAll();
    Article find(long id);
    void insertArticle(Article article);
    void removeArticle(long id);
    void removeComment(long articleId, long commentId);
    void insertComment(long articleId, Comment comment);
    List<Article> findByMenuId(long menuId);
    List<Article> findByTagId(long tagId);
}
