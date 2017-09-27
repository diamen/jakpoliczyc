package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Configuration;
import pl.jakpoliczyc.web.dto.ConfigurationDto;

import java.util.List;
import java.util.Optional;

public interface ConfigurationService {

    List<Configuration> findAll();

    Optional<Configuration> find(final String key);

    void insert(final List<ConfigurationDto> configurations);

}
