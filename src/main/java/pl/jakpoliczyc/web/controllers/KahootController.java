package pl.jakpoliczyc.web.controllers;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.entities.Kahoot;
import pl.jakpoliczyc.dao.services.KahootService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class KahootController {

    @Autowired
    private KahootService kahootService;

    @ResponseBody
    @RequestMapping(value = "/kahoot", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKahootConfiguration() {
        final List<Kahoot> kahoots = kahootService.findAll();
        if (kahoots == null || kahoots.isEmpty()) {
            throw new ResourceNotFoundException("None kahoots found!");
        }
        return new ResponseEntity<>(kahoots, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/kahoot", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postConfiguration(@Valid @RequestBody final Kahoot kahoot) {
        kahootService.insert(Lists.newArrayList(kahoot));
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/kahoot/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteKahoot(@PathVariable long id) {
        kahootService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
