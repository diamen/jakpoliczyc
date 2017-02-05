package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Configuration;

import java.util.List;

public interface ConfigurationService {
    List<Configuration> findAll();
}
