package pl.jakpoliczyc.dao.repos;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Menu;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service("menuServiceImpl")
@Transactional
public class MenuServiceImpl implements MenuService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Menu> findAll() {
        return entityManager.createQuery("SELECT e FROM Menu e", Menu.class).getResultList()
                .stream().filter(e -> e.getParent() == null).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Menu find(long id) {
        return entityManager.find(Menu.class, id);
    }

    @Transactional
    public void save(Menu menu) {
        entityManager.persist(menu);
    }
}
