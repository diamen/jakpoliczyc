package pl.jakpoliczyc.dao.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Configuration;
import pl.jakpoliczyc.dao.repos.ConfigurationRepository;
import pl.jakpoliczyc.dao.services.ConfigurationService;
import pl.jakpoliczyc.web.dto.ConfigurationDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Configuration> findAll() {
        return configurationRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Configuration> find(final String key) {
        return configurationRepository.find(key);
    }

    @Override
    public void replace(final List<ConfigurationDto> configurationDtos) {
        configurationRepository.deleteAll();
        configurationRepository.insert(configurationDtos.stream().map(configDto -> {
            final Configuration configuration = new Configuration();
            BeanUtils.copyProperties(configDto, configuration);
            return configuration;
        }).collect(Collectors.toList()));
    }
}
