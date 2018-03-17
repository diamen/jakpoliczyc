package pl.jakpoliczyc.dao.services.impl;

import org.springframework.stereotype.Component;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.services.MenuConsumer;
import pl.jakpoliczyc.dao.services.MenuTreeTraverser;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MenuTreeTraverserImpl implements MenuTreeTraverser {

    @Override
    public List<Menu> findDescendants(Menu menu) {
        return flattened(menu).collect(Collectors.toList());
    }

    @Override
    public void siblingCallback(Menu menu, MenuConsumer menuConsumer) {
        for (int i = 0; i < menu.getParent().getSubmenus().size(); i++) {
            menuConsumer.accept(menu.getParent().getSubmenus().get(i));
        }
    }

    @Override
    public void childCallback(Menu menu, MenuConsumer menuConsumer) {
        for (int i = 0; i < menu.getSubmenus().size(); i++) {
            menuConsumer.accept(menu.getSubmenus().get(i));
        }
    }

    @Override
    public void sortEachLevel(final List<Menu> menus, Comparator<Menu> comparator) {
        menus.sort(comparator);
        menus.forEach(menu -> sortEachLevel(menu.getSubmenus(), comparator));
    }

    protected Stream<Menu> flattened(final Menu menu) {
        return Stream.concat(Stream.of(menu), menu.getSubmenus().stream().flatMap(this::flattened));
    }

}
