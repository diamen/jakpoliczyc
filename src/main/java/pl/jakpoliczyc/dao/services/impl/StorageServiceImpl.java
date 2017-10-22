package pl.jakpoliczyc.dao.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.converters.UrlToStringConverter;
import pl.jakpoliczyc.dao.entities.Stag;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.repos.StagRepository;
import pl.jakpoliczyc.dao.repos.StorageRepository;
import pl.jakpoliczyc.dao.services.ArticleService;
import pl.jakpoliczyc.dao.services.StorageService;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StorageCompressedDto;
import pl.jakpoliczyc.web.dto.StorageDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private StagRepository stagRepository;

    private UrlToStringConverter converter = new UrlToStringConverter();

    @Override
    public void save(StorageDto wrapper) {
        Storage storage = new Storage();
        storage.setStags(wrapper.getStags() != null ? prepareStags(wrapper.getStags()) : null);
        storage.setStory(wrapper.getStory());
        storage.setUrl(converter.convertToEntityAttribute(wrapper.getUrl()));
        storage.setKahoot(wrapper.getKahoot());
        storageRepository.insert(storage);
    }

    @Override
    public void update(long storageId, StorageDto wrapper) {
        Storage storage = storageRepository.find(storageId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Storage resource with id %d not found", storageId)));
        storage.setStory(wrapper.getStory());
        storage.setStags(wrapper.getStags() != null ? prepareStags(wrapper.getStags()) : null);
        storage.setAddedDate(new Date());
        storage.setUrl(converter.convertToEntityAttribute(wrapper.getUrl()));
        storage.setKahoot(wrapper.getKahoot());
        storageRepository.insert(storage);
    }

    @Override
    public void delete(long storageId) {
        storageRepository.delete(storageId);
    }

    @Transactional(readOnly = true)
    @Override
    public Storage find(long id) {
        return storageRepository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Storage resource with id %d not found", id)));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StorageCompressedDto> findAll(final Pageable pageable) {
        final Page<Storage> storages = storageRepository.findAll(pageable);
        return new PageImpl<>(convertToCompressedList(storages.getContent()), pageable, storages.getTotalPages());
    }

    @Transactional
    @Override
    public void publish(long storageId, List<MenuDto> menus) {
        Storage storage = find(storageId);
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto();
        storyMenuTagDto.setStory(storage.getStory());
        storyMenuTagDto.setMenus(menus);
        storyMenuTagDto.setTags(storage.getStags().stream().map(Stag::getName).collect(Collectors.toList()));
        storyMenuTagDto.setKahoot(storage.getKahoot());
        storageRepository.delete(storageId);
        articleService.save(storyMenuTagDto);
    }

    private List<Stag> prepareStags(List<String> names) {
        return names.stream().map(e -> {
            Optional<Stag> stagOptional = stagRepository.findByName(e);
            if (stagOptional.isPresent()) {
                return stagOptional.get();
            } else {
                Stag stag = new Stag();
                stag.setName(e);
                stagRepository.save(stag);
                return stag;
            }
        }).collect(Collectors.toList());
    }

    private List<StorageCompressedDto> convertToCompressedList(List<Storage> storages) {
        return storages.stream().map(storage -> {
            return new StorageCompressedDto(storage.getId(), storage.getStory() != null ? storage.getStory().getTitle() : null,
                    storage.getAddedDate(), storage.getStags());
        }).collect(Collectors.toList());
    }
}
