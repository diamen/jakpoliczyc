package pl.jakpoliczyc.dao.repos;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Storage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class StorageRepositoryImpl implements StorageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Optional<Storage> find(long id) {
        return Optional.ofNullable(entityManager.find(Storage.class, id));
    }

    @Transactional(readOnly = true)
    public List<Storage> findAll() {
        return entityManager.createQuery("SELECT e FROM STORAGES e", Storage.class).getResultList();
    }

    public void insert(Storage storage) {
        entityManager.persist(storage);
    }

    public void delete(long id) {
        Storage storage = entityManager.find(Storage.class, id);
        entityManager.remove(storage);
    }

}
