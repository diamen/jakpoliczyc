package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.User;

public interface UserService {
    User findByUsername(String username);
}
