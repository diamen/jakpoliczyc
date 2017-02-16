package pl.jakpoliczyc.dao.repos;

import pl.jakpoliczyc.dao.entities.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findAll();
}
