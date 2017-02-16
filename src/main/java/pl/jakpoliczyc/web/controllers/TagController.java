package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jakpoliczyc.dao.entities.Tag;
import pl.jakpoliczyc.dao.repos.TagService;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @ResponseBody
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public List<Tag> getTags() {
        return tagService.findAll();
    }

}
