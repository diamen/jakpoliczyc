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
import javax.persistence.Query;
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

    @Override
    public void insertArticle(Article article) {
        entityManager.persist(article);
    }

    @Override
    public void removeArticle(long id) {
        Article article = entityManager.find(Article.class, id);
        entityManager.remove(article);
    }

    @Override
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
    public Page<Article> findByMenuId(final Pageable pageable, final long menuId) {
        final String query = String.format("SELECT e FROM ARTICLES e WHERE e.menu.id = :id %s", RepositoryUtils.sortToStringQuery(pageable.getSort(), Article.class));
        final List<Article> articles = entityManager.createQuery(query, Article.class)
                .setParameter("id", menuId)
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        final long total = entityManager.createQuery("SELECT COUNT(e.articles) FROM Menu e WHERE e.id = :id", Long.class)
                .setParameter("id", menuId)
                .getSingleResult();
        return new PageImpl<>(articles, pageable, total);
    }

    @Override
    public Page<Article> findByTagId(final Pageable pageable, final List<Long> ids) {

        String idsQuery = "";

        for (int i = 1; i <= ids.size(); i++) {
            idsQuery += "?" + i;

            if (i != ids.size()) {
                idsQuery += ", ";
            }
        }

        final String mainQuery = String.format("SELECT * FROM %s.ARTICLES WHERE id in (SELECT ART_ID FROM %s.ART_TAG WHERE TAG_ID IN (%s)) %s",
                RepositoryUtils.PERSISTENCE_UNIT_NAME, RepositoryUtils.PERSISTENCE_UNIT_NAME, idsQuery, RepositoryUtils.sortToStringNativeQuery(pageable.getSort()));

        final String countQuery = String.format("SELECT COUNT(*) FROM %s.ARTICLES WHERE id in (SELECT ART_ID FROM %s.ART_TAG WHERE TAG_ID IN (%s))", RepositoryUtils.PERSISTENCE_UNIT_NAME, RepositoryUtils.PERSISTENCE_UNIT_NAME, idsQuery);

        Query nativeMainQuery = entityManager.createNativeQuery(mainQuery, Article.class);
        Query nativeCountQuery = entityManager.createNativeQuery(countQuery);

        for (int i = 1; i <= ids.size(); i++) {
            nativeMainQuery = nativeMainQuery.setParameter(i, ids.get(i - 1));
            nativeCountQuery = nativeCountQuery.setParameter(i, ids.get(i - 1));
        }

        final List<Article> articles = nativeMainQuery
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        final long total = (Long) nativeCountQuery.getSingleResult();

        return new PageImpl<>(articles, pageable, total);
    }
}
