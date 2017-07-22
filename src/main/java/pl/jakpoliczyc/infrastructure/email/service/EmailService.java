package pl.jakpoliczyc.infrastructure.email.service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface EmailService {
    void sendMultiEmail(List<String> emailAddressesTo, String subject, String content) throws MessagingException, IOException;
    void sendFromToEmail(final String from, final String subject, String content) throws MessagingException;
}
