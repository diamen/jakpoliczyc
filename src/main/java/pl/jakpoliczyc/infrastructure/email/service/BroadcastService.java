package pl.jakpoliczyc.infrastructure.email.service;

import javax.mail.MessagingException;

public interface BroadcastService {
    void broadcast(String title, String content) throws MessagingException;
}
