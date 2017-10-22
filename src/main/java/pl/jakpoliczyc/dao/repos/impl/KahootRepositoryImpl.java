package pl.jakpoliczyc.dao.repos.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Kahoot;
import pl.jakpoliczyc.dao.repos.KahootRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class KahootRepositoryImpl implements KahootRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Kahoot> findAll() {
        return entityManager.createQuery("SELECT e FROM KAHOOT e", Kahoot.class).getResultList();
    }

    @Override
    public void insert(List<Kahoot> kahoots) {
        kahoots.forEach(kahoot -> {
            final Kahoot existingKahoot = entityManager.find(Kahoot.class, kahoot.getId());
            if (existingKahoot != null) {
                existingKahoot.setKahootDifficulties(kahoot.getKahootDifficulties());
                existingKahoot.setTitle(kahoot.getTitle());
                entityManager.persist(existingKahoot);
            } else {
                entityManager.persist(kahoot);
            }
        });
    }

    @Override
    public void delete(final long id) {
        Optional.ofNullable(entityManager.find(Kahoot.class, id)).ifPresent(entityManager::remove);
    }
}
