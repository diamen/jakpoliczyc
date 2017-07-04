package pl.jakpoliczyc.dao.repos.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.repos.StorageRepository;

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
    public Page<Storage> findAll(final Pageable pageable) {
        final List<Storage> storages = entityManager.createQuery("SELECT e FROM STORAGES  e", Storage.class)
                .setFirstResult(pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        final long total = entityManager.createQuery("SELECT COUNT(e) FROM STORAGES e", Long.class).getSingleResult();
        return new PageImpl<>(storages, pageable, total);
    }

    public void insert(Storage storage) {
        entityManager.persist(storage);
    }

    public void delete(long id) {
        Storage storage = entityManager.find(Storage.class, id);
        entityManager.remove(storage);
    }

}
