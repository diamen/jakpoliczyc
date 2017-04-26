package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Subscriber;

import java.util.List;
import java.util.Optional;

public interface SubscriberRepository {
    List<Subscriber> findAll();
    Optional<Subscriber> find(String email);
    void remove(Subscriber subscriber);
    void insert(Subscriber subscriber);
}
