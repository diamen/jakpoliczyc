package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Storage;

import java.util.List;

public interface StorageService {
    Storage find(long id);
    List<Storage> findAll();
    void insert(Storage storage);
    void remove(long id);
}
