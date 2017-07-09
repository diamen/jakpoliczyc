package pl.jakpoliczyc.dao.repos.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Comment;
import pl.jakpoliczyc.dao.repos.ArticleRepository;
import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Page<Article> findAll(final Pageable pageable) {
        final String query = String.format("SELECT e FROM ARTICLES e %s", RepositoryUtils.sortToStringQuery(pageable.getSort(), Article.class));
        final List<Article> articles = entityManager.createQuery(query, Article.class)
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        final long total = entityManager.createQuery("SELECT COUNT(e) FROM ARTICLES e", Long.class).getSingleResult();
        return new PageImpl<>(articles, pageable, total);
    }

    @Override
    public Article find(long id) {
        return entityManager.find(Article.class, id);
    }

    public void insertArticle(Article article) {
        entityManager.persist(article);
    }

    @Override
    public void removeArticle(long id) {
        Article article = entityManager.find(Article.class, id);
        entityManager.remove(article);
    }

    @Override
    @Transactional
    public void removeComment(long articleId, long commentId) {
        Article article = entityManager.find(Article.class, articleId);
        Comment comment = article.getComments().stream().filter(e -> e.getId() == commentId).findFirst().get();
        entityManager.remove(comment);
        article.getComments().remove(comment);
    }

    @Override
    public void insertComment(long articleId, Comment comment) {
        Article article = entityManager.find(Article.class, articleId);
        article.getComments().add(comment);
        entityManager.persist(article);
    }

    @Override
    public List<Article> findByMenuId(long menuId) {
        return entityManager.createQuery("SELECT e.articles FROM Menu e WHERE e.id = :id", Article.class)
                .setParameter("id", menuId).getResultList();
    }

    @Override
    public List<Article> findByTagId(long tagId) {
        return entityManager.createQuery("SELECT e.articles FROM TAGS e WHERE e.id = :id", Article.class)
                .setParameter("id", tagId).getResultList();
    }
}
