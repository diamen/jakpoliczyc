package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Menu;

import java.util.List;

public interface MenuService {
    Menu find(long id);
    List<Menu> findAll();
    void save(Menu menu);
}
