package pl.jakpoliczyc.dao.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Subscriber;
import pl.jakpoliczyc.dao.repos.SubscriberRepository;
import pl.jakpoliczyc.dao.services.SubscriberService;

import java.util.List;

@Transactional
@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public boolean insertOrRemove(Subscriber subscriber) {
        boolean exists = subscriberRepository.find(subscriber.getEmail()).isPresent();
        if (exists) {
            subscriberRepository.remove(subscriber);
        } else {
            subscriberRepository.insert(subscriber);
        }
        return !exists;
    }
}
