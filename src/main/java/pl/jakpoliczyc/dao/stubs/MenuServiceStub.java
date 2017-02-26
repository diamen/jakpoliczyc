package pl.jakpoliczyc.dao.stubs;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.repos.MenuService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("menuServiceStub")
@Transactional
public class MenuServiceStub implements MenuService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Menu find(long id) {
        return null;
    }

    @Override
    public List<Menu> findAll() {
        return entityManager.createQuery("SELECT e FROM Menu e", Menu.class).getResultList();
    }

    @Override
    public void save(Menu menu) {
        entityManager.persist(menu);
    }
}
