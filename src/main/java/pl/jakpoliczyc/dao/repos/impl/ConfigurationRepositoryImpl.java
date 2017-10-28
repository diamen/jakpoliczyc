package pl.jakpoliczyc.dao.repos.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Configuration;
import pl.jakpoliczyc.dao.repos.ConfigurationRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ConfigurationRepositoryImpl implements ConfigurationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<Configuration> find(String key) {
        final Configuration config = entityManager.createQuery("SELECT e FROM Configuration e WHERE e.keyy = :keyy", Configuration.class)
                .setParameter("keyy", key).getSingleResult();
        return Optional.ofNullable(config);
    }

    @Transactional(readOnly = true)
    public List<Configuration> findAll() {
        return entityManager.createQuery("SELECT e FROM Configuration e", Configuration.class)
                .getResultList();
    }

    @Override
    public void insert(List<Configuration> configurations) {
        configurations.forEach(configuration -> entityManager.persist(configuration));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll() {
        entityManager.clear();
        entityManager.createQuery("DELETE FROM Configuration e", Configuration.class)
                .executeUpdate();
    }
}
