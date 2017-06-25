package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Menu;

import java.util.List;

public interface MenuRepository {
    Menu find(long id);
    List<Menu> findAll();
    List<Menu> findAllUnparsed();
    void remove(Menu menu);
    void save(Menu menu);
}
