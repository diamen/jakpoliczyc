package pl.jakpoliczyc.dao.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.jakpoliczyc.dao.entities.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageRepository {
    Optional<Storage> find(long id);
    Page<Storage> findAll(final Pageable pageable);
    void insert(Storage storage);
    void delete(long id);
}
