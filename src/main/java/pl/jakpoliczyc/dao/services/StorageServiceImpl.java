package pl.jakpoliczyc.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Stag;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.repos.StagRepository;
import pl.jakpoliczyc.dao.repos.StorageRepository;
import pl.jakpoliczyc.web.dto.StorageDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private StagRepository stagRepository;

    @Override
    public void save(StorageDto wrapper) {
        Storage storage = new Storage();
        storage.setStags(wrapper.getStags() != null ? prepareStags(wrapper.getStags()) : null);
        storage.setStory(wrapper.getStory());
        storageRepository.insert(storage);
    }

    @Override
    public void update(long storageId, StorageDto wrapper) {
        Storage storage = storageRepository.find(storageId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Storage resource with id %d not found", storageId)));
        storage.setStory(wrapper.getStory());
        storage.setStags(wrapper.getStags() != null ? prepareStags(wrapper.getStags()) : null);
        storage.setAddedDate(new Date());
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
    public List<Storage> findAll() {
        return storageRepository.findAll();
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
}
