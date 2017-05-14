package pl.jakpoliczyc.dao.repos.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Stag;
import pl.jakpoliczyc.dao.repos.StagRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StagRepositoryImpl implements StagRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Stag> findAll() {
        return entityManager.createQuery("SELECT e FROM STAGS e", Stag.class).getResultList();
    }

    @Override
    public List<Stag> in(List<String> names) {
        return entityManager.createQuery("SELECT e FROM STAGS e where e.name IN :names")
                .setParameter("names", names).getResultList();
    }

    public Optional<Stag> findByName(String name) {
        List<Stag> stags = entityManager.createQuery("SELECT e FROM STAGS e where e.name = :name", Stag.class)
                .setParameter("name", name).getResultList();
        if (stags.isEmpty()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(stags.get(0));
    }

    @Override
    public void save(Stag stag) {
        entityManager.persist(stag);
    }

}
