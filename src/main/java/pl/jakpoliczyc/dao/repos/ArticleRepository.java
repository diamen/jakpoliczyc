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
    Page<Article> findByMenuId(final Pageable pageable, final long menuId);
    Page<Article> findByTagId(final Pageable pageable, final List<Long> ids);
}
