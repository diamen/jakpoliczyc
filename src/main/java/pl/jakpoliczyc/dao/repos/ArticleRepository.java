package pl.jakpoliczyc.dao.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Comment;

import java.util.List;

public interface ArticleRepository {
    Page<Article> findAll(final Pageable pageable);
    Article find(long id);
    void insertArticle(Article article);
    void removeArticle(long id);
    void removeComment(long articleId, long commentId);
    void insertComment(long articleId, Comment comment);
    List<Article> findByMenuId(long menuId);
    List<Article> findByTagId(long tagId);
}
