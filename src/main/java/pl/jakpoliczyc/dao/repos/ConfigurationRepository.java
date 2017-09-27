package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Configuration;

import java.util.List;
import java.util.Optional;

public interface ConfigurationRepository {
    Optional<Configuration> find(String key);
    List<Configuration> findAll();
    void insert(final List<Configuration> configurations);
    void deleteAll();
}
