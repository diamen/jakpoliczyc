package pl.jakpoliczyc.dao.repos;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Storage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("storageServiceImpl")
@Transactional
public class StorageServiceImpl implements StorageService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Storage find(long id) {
        return entityManager.find(Storage.class, id);
    }

    @Transactional(readOnly = true)
    public List<Storage> findAll() {
        return entityManager.createQuery("SELECT e FROM STORAGES e", Storage.class).getResultList();
    }

    public void insert(Storage storage) {
        entityManager.persist(storage);
    }

    public void remove(long id) {
        Storage storage = entityManager.find(Storage.class, id);
        entityManager.remove(storage);
    }

}
