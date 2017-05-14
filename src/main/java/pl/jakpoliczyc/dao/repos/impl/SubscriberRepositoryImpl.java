package pl.jakpoliczyc.dao.repos.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Subscriber;
import pl.jakpoliczyc.dao.repos.SubscriberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class SubscriberRepositoryImpl implements SubscriberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Subscriber> findAll() {
        return entityManager.createQuery("SELECT e FROM SUBSCRIBERS e", Subscriber.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Subscriber> find(String email) {
        List<Subscriber> subscribers = entityManager.createQuery("SELECT e FROM SUBSCRIBERS  e WHERE e.email = :email", Subscriber.class)
                .setParameter("email", email).getResultList();
        return Optional.ofNullable(subscribers.isEmpty() ? null: subscribers.get(0));
    }

    @Override
    public void remove(Subscriber subscriber) {
        subscriber = entityManager.merge(subscriber);
        entityManager.remove(subscriber);
    }

    @Override
    public void insert(Subscriber subscriber) {
        entityManager.merge(subscriber);
    }
}
