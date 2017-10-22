package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Kahoot;

import java.util.List;

public interface KahootRepository {
    List<Kahoot> findAll();
    void insert(final List<Kahoot> kahoots);
    void delete(final long id);
}
