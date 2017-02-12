package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jakpoliczyc.dao.entities.Configuration;
import pl.jakpoliczyc.dao.repos.ConfigurationService;

import java.util.List;

@RestController
public class TagsController {

    @Autowired
    private ConfigurationService configurationService;

    @ResponseBody
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String test() {
        List<Configuration> confs = configurationService.findAll();
        System.out.println(confs.get(0).getValue());
        return confs.get(0).getValue();
    }

}
