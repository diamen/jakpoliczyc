package pl.jakpoliczyc.dao.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StorageCompressedDto;
import pl.jakpoliczyc.web.dto.StorageDto;

import java.util.List;

public interface StorageService {
    void save(StorageDto wrapper);
    void update(long storageId, StorageDto wrapper);
    void delete(long storageId);
    Storage find(long id);
    Page<StorageCompressedDto> findAll(final Pageable pageable);
    void publish(long storageId, List<MenuDto> menus);
}
