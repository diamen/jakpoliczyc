package pl.jakpoliczyc.dao.repos.impl;

import org.eclipse.persistence.config.QueryHints;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.repos.MenuRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Repository
@Transactional
public class MenuRepositoryImpl implements MenuRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Menu> findAll() {
        return entityManager.createQuery("SELECT e FROM Menu e", Menu.class)
                .setHint(QueryHints.REFRESH, true)
                .getResultList()
                .stream()
                .filter(e -> e.getParent() == null)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Menu> findAllUnparsed() {
        return entityManager.createQuery("SELECT e FROM Menu e", Menu.class).setHint(QueryHints.REFRESH, true).getResultList();
    }

    @Transactional(readOnly = true)
    public Optional<Menu> find(long id) {
        return Optional.ofNullable(entityManager.find(Menu.class, id));
    }

    @Override
    public void remove(Menu menu) {
        if (entityManager.find(Menu.class, menu.getId()) != null) {
            entityManager.remove(entityManager.contains(menu) ? menu : entityManager.merge(menu));
            entityManager.flush();
        }
    }

    @Override
    public void resetIdSequence() {
        entityManager.createNativeQuery("ALTER TABLE MENU AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE ARTICLES AUTO_INCREMENT = 1").executeUpdate();
    }

    @Override
    public void update(Menu menu) {
        if (!findAllUnparsed().contains(menu)) {
            entityManager.persist(menu);
            return;
        }
        if (!entityManager.contains(menu)) {
            entityManager.merge(menu);
        }
    }
}
