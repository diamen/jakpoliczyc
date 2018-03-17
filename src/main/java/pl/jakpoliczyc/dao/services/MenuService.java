package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    void clearAndSaveMenu(final List<Menu> menus);

    Optional<Menu> find(long id);

    List<Menu> findAll();

}
