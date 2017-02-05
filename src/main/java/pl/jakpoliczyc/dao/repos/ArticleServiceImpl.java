package pl.jakpoliczyc.dao.repos;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("articleServiceImpl")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Article> findAll() {
        return entityManager.createQuery("SELECT e FROM ARTICLES e", Article.class).getResultList();
    }

    public void insert(Article article) {
        entityManager.persist(article);
    }

    public void remove(long id) {
        Article article = entityManager.find(Article.class, id);
        entityManager.remove(article);
    }

}
