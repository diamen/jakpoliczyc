package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Article;

import java.util.List;

public interface ArticleService {
    List<Article> findAll();
    Article find(long id);
    void insert(Article article);
    void remove(long id);
}
