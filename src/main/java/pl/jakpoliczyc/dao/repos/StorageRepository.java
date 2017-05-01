package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Storage;

import java.util.List;
import java.util.Optional;

public interface StorageRepository {
    Optional<Storage> find(long id);
    List<Storage> findAll();
    void insert(Storage storage);
    void delete(long id);
}
