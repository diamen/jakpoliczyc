package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.jakpoliczyc.dao.entities.Tag;
import pl.jakpoliczyc.dao.repos.TagService;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> getTags() {
        return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }

}
