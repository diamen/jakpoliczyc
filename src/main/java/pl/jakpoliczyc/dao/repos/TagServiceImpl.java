package pl.jakpoliczyc.dao.repos;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("tagServiceImpl")
@Transactional
public class TagServiceImpl implements TagService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return entityManager.createQuery("SELECT e FROM TAGS e", Tag.class).getResultList();
    }

}
