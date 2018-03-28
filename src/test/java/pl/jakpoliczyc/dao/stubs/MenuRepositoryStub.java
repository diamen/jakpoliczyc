package pl.jakpoliczyc.dao.stubs;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.repos.MenuRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class MenuRepositoryStub implements MenuRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Optional<Menu> find(long id) {
        return Optional.ofNullable(entityManager.find(Menu.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> findAll() {
        return entityManager.createQuery("SELECT e FROM Menu e", Menu.class).getResultList();
    }

    @Override
    public List<Menu> findAllUnparsed() {
        return null;
    }

    @Override
    public void remove(Menu menu) {

    }

    @Override
    public void resetIdSequence() {

    }

    @Override
    public void update(Menu menu) {
        entityManager.persist(menu);
    }
}
