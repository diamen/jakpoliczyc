package pl.jakpoliczyc.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        return "test";
    }

}
