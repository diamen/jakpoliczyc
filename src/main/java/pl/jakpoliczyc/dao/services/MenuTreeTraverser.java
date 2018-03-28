package pl.jakpoliczyc.dao.services;

import pl.jakpoliczyc.dao.entities.Menu;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public interface MenuTreeTraverser {

    List<Menu> findDescendants(final Menu menu);

    void siblingCallback(final Menu menu, MenuConsumer menuConsumer);

    void childCallback(final Menu menu, MenuConsumer menuConsumer);

    void sortEachLevel(final List<Menu> menus, Comparator<Menu> comparator);

    Stream<Menu> flattened(final Menu menu);

}
