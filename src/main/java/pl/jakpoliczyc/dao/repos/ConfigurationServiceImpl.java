package pl.jakpoliczyc.dao.repos;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Configuration;
import pl.jakpoliczyc.dao.entities.Subscriber;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service("configurationServiceImpl")
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<Configuration> find(String key) {
        List<Configuration> configs = entityManager.createQuery("SELECT e FROM Configuration e WHERE e.keyy = :keyy", Configuration.class)
                .setParameter("keyy", key).getResultList();
        return Optional.ofNullable(configs.isEmpty() ? null: configs.get(0));
    }

    @Transactional(readOnly = true)
    public List<Configuration> findAll() {
        return entityManager.createQuery("SELECT e FROM Configuration e", Configuration.class).getResultList();
    }
}
