package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Subscriber;

import java.util.List;

public interface SubscriberService {
    List<Subscriber> findAll();
    boolean insertOrRemove(Subscriber subscriber);
}
