package pl.jakpoliczyc.infrastructure.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jakpoliczyc.dao.services.SubscriberService;
import pl.jakpoliczyc.infrastructure.email.service.BroadcastService;
import pl.jakpoliczyc.infrastructure.email.service.EmailService;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BroadcastServiceImpl implements BroadcastService {

    @Autowired
    private SubscriberService subscriberService;
    @Autowired
    private EmailService emailService;

    @Value("${smtp.username}")
    private String receiver;

    @Override
    public void broadcast(String title, String content) throws MessagingException {
        List<String> emailAddressesTo = subscriberService.findAll().stream().map(e -> e.getEmail()).collect(Collectors.toList());
        emailService.sendEmail(null, emailAddressesTo, title, content);
    }

    @Override
    public void contact(String address, String title, String content) throws MessagingException {
        emailService.sendEmail(address, Arrays.asList(receiver), title, content);
    }
}