package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Kahoot;

import java.util.List;

public interface KahootService {
    List<Kahoot> findAll();
    void insert(final List<Kahoot> kahoots);
    void delete(final long id);
}
