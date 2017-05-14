package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.repos.MenuRepository;

import java.util.List;

@RestController
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @ResponseBody
    @RequestMapping(value = "/menu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMenus() {
        List<Menu> menus = menuRepository.findAll();
        if (menus == null || menus.isEmpty()) {
            throw new ResourceNotFoundException("None menus found");
        }
        return new ResponseEntity<>(menuRepository.findAll(), HttpStatus.OK);
    }

}
