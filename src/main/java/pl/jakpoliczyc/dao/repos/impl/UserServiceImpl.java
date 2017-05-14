package pl.jakpoliczyc.dao.repos.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.User;
import pl.jakpoliczyc.dao.repos.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return entityManager.createQuery("SELECT e FROM USERS e WHERE e.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}
