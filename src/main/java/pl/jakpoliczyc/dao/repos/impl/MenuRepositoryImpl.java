package pl.jakpoliczyc.dao.repos.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.config.Caches;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.repos.MenuRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Repository
@Transactional
public class MenuRepositoryImpl implements MenuRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Cacheable(value = Caches.MENU_CACHE)
    @Transactional(readOnly = true)
    public List<Menu> findAll() {
        return entityManager.createQuery("SELECT e FROM Menu e", Menu.class).getResultList()
                .stream().filter(e -> e.getParent() == null).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Menu> findAllUnparsed() {
        return entityManager.createQuery("SELECT e FROM Menu e", Menu.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Menu find(long id) {
        return entityManager.find(Menu.class, id);
    }

    @CacheEvict(value = Caches.MENU_CACHE, allEntries = true)
    @Override
    public void remove(Menu menu) {
        entityManager.remove(menu);
    }

    @CacheEvict(value = Caches.MENU_CACHE, allEntries = true)
    @Override
    public void save(Menu menu) {
        entityManager.persist(menu);
    }
}
