package pl.jakpoliczyc.dao.repos;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("testServiceImpl")
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Configuration> findAll() {
        return entityManager.createQuery("SELECT e FROM Configuration e", Configuration.class).getResultList();
    }
}
