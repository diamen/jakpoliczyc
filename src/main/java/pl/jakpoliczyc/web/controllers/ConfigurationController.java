package pl.jakpoliczyc.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.entities.Configuration;
import pl.jakpoliczyc.dao.services.ConfigurationService;
import pl.jakpoliczyc.web.dto.ConfigurationDto;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @ResponseBody
    @RequestMapping(value = "/configuration", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getConfiguration() {
        final List<Configuration> configs = configurationService.findAll();
        if (configs == null || configs.isEmpty()) {
            throw new ResourceNotFoundException("None configs found!");
        }
        return new ResponseEntity<>(configs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/configuration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postConfiguration(@Valid @RequestBody final List<ConfigurationDto> configurationDtos) {
        configurationService.replace(configurationDtos);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
