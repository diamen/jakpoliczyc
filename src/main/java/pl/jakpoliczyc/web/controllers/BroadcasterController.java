package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.jakpoliczyc.infrastructure.email.service.BroadcastService;
import pl.jakpoliczyc.web.dto.EmailContentDto;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
public class BroadcasterController {

    @Autowired
    private BroadcastService broadcastService;

    @RequestMapping(value = "/broadcaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> broadcast(@Valid @RequestBody EmailContentDto wrapper) throws MessagingException {
        broadcastService.broadcast(wrapper.getTitle(), wrapper.getContent());
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
