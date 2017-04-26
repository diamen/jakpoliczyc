package pl.jakpoliczyc.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Subscriber;
import pl.jakpoliczyc.dao.repos.SubscriberRepository;

import java.util.List;

@Transactional
@Service("subscriberServiceImpl")
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

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
