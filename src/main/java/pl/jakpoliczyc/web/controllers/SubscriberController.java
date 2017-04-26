package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.jakpoliczyc.dao.entities.Subscriber;
import pl.jakpoliczyc.dao.services.SubscriberService;

import javax.validation.Valid;

@RestController
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @RequestMapping(value = "/subscriber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> insertOrDeleteSubscriber(@Valid @RequestBody Subscriber subscriber) {
        boolean inserted = subscriberService.insertOrRemove(subscriber);
        HttpStatus status = inserted ? HttpStatus.CREATED : HttpStatus.OK;
        return new ResponseEntity<Object>(null, status);
    }

}
