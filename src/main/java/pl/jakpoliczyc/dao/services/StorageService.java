package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StorageDto;

import java.util.List;

public interface StorageService {
    void save(StorageDto wrapper);
    void update(long storageId, StorageDto wrapper);
    void delete(long storageId);
    Storage find(long id);
    List<Storage> findAll();
    void publish(long storageId, List<MenuDto> menus);
}
