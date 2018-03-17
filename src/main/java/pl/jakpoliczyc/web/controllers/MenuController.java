package pl.jakpoliczyc.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.services.MenuService;
import pl.jakpoliczyc.web.serialization.FilterBuilder;
import pl.jakpoliczyc.web.serialization.Filters;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getMenus() throws JsonProcessingException {
        final List<Menu> menus = menuService.findAll();
        if (menus == null || menus.isEmpty()) {
            throw new ResourceNotFoundException("None menus found");
        }

        final String json = new FilterBuilder()
                .filterName(Filters.FILTER_MENU)
                .objectToSerialize(menus)
                .excludedProperties("articles")
                .buildJson()
                .orElse(StringUtils.EMPTY);

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @RequestMapping(value = "/menuWithArticle", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getMenuWithArticle() throws JsonProcessingException {
        final List<Menu> menus = menuService.findAll();
        if (menus == null || menus.isEmpty()) {
            throw new ResourceNotFoundException("None menus found");
        }

        final String json = new FilterBuilder()
                .filterName(Filters.FILTER_MENU)
                .objectToSerialize(menus)
                .buildJson()
                .orElse(StringUtils.EMPTY);

        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping(value = "/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postMenu(@RequestBody final List<Menu> menus) {
        menuService.clearAndSaveMenu(menus);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
