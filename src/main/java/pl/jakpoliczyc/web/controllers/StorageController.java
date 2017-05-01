package pl.jakpoliczyc.web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.services.StorageService;
import pl.jakpoliczyc.web.common.View;
import pl.jakpoliczyc.web.dto.StorageDto;

import java.util.List;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @JsonView(View.Compress.class)
    @RequestMapping(value = "/storage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStorageArticles() {
        List<Storage> articlesInStorage = storageService.findAll();
        if (CollectionUtils.isEmpty(articlesInStorage)) {
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
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/storage/{id:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStorage(@PathVariable long id) {
        storageService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
