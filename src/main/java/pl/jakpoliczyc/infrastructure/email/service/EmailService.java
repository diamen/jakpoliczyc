package pl.jakpoliczyc.infrastructure.email.service;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {
    void sendEmail(List<String> emailAddressesTo, String subject, String content) throws MessagingException;
}
