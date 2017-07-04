package pl.jakpoliczyc.web.controllers;

import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.services.StorageService;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StorageCompressedDto;
import pl.jakpoliczyc.web.dto.StorageDto;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/storage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStorageArticles(final Pageable pageable) {
        Page<StorageCompressedDto> articlesInStorage = storageService.findAll(pageable);
        if (articlesInStorage == null || CollectionUtils.isEmpty(articlesInStorage.getContent())) {
            throw new ResourceNotFoundException("None storage article found");
        }
        return new ResponseEntity<>(articlesInStorage, HttpStatus.OK);
    }

    @RequestMapping(value = "/storage/{id:[0-9]*}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStorageArticle(@PathVariable long id) {
        return new ResponseEntity<>(storageService.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/storage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertStorage(@RequestBody StorageDto wrapper) {
        Preconditions.checkNotNull(wrapper);
        storageService.save(wrapper);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/storage/{id:[0-9]*}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStorage(@PathVariable long id, @RequestBody StorageDto storageDto) {
        storageService.update(id, storageDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/storage/{id:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStorage(@PathVariable long id) {
        storageService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/storage/publish/{id:[0-9]*}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> publishStorage(@PathVariable long id, @Valid @RequestBody List<MenuDto> menus) {
        storageService.publish(id, menus);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
