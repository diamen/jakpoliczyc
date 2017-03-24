package pl.jakpoliczyc.dao.repos;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Tag;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service("tagServiceImpl")
@Transactional
public class TagServiceImpl implements TagService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return entityManager.createQuery("SELECT e FROM TAGS e", Tag.class).getResultList();
    }

    @Override
    public List<Tag> in(List<String> names) {
        return entityManager.createQuery("SELECT e FROM TAGS e where e.name IN :names")
                .setParameter("names", names).getResultList();
    }

    public Optional<Tag> findByName(String name) {
        List<Tag> tags = entityManager.createQuery("SELECT e FROM TAGS e where e.name = :name", Tag.class)
                .setParameter("name", name).getResultList();
        if (tags.isEmpty()) {
            return Optional.ofNullable(null);
        }
        return Optional.of(tags.get(0));
    }

    @Override
    public void save(Tag tag) {
        entityManager.persist(tag);
    }

}
