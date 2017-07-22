package pl.jakpoliczyc.infrastructure.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jakpoliczyc.dao.entities.Subscriber;
import pl.jakpoliczyc.dao.services.SubscriberService;
import pl.jakpoliczyc.infrastructure.email.service.BroadcastService;
import pl.jakpoliczyc.infrastructure.email.service.EmailService;

import javax.mail.MessagingException;
import java.io.IOException;
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
    public void broadcast(String title, String content) throws MessagingException, IOException {
        List<String> emailAddressesTo = subscriberService.findAll().stream().map(Subscriber::getEmail)
                .collect(Collectors.toList());
        emailService.sendMultiEmail(emailAddressesTo, title, content);
    }

    @Override
    public void contact(String address, String title, String content) throws MessagingException {
        emailService.sendFromToEmail(address, title, content);
    }
}
