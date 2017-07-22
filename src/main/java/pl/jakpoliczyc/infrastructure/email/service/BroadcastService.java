package pl.jakpoliczyc.infrastructure.email.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface BroadcastService {
    void broadcast(String title, String content) throws MessagingException, IOException;
    void contact(String address, String title, String content) throws MessagingException;
}
