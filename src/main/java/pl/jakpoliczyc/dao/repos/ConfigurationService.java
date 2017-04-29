package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Configuration;

import java.util.List;
import java.util.Optional;

public interface ConfigurationService {
    Optional<Configuration> find(String key);
    List<Configuration> findAll();
}
