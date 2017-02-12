package pl.jakpoliczyc.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagsController {

    @ResponseBody
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public String test() {
        System.out.println("tags");
        return "tags";
    }

}
