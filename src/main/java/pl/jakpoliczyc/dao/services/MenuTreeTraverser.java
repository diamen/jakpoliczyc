package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Menu;

import java.util.List;

public interface MenuTreeTraverser {

    List<Menu> findDescendants(final Menu menu);

    void siblingCallback(final Menu menu, MenuConsumer menuConsumer);

    void childCallback(final Menu menu, MenuConsumer menuConsumer);

}
