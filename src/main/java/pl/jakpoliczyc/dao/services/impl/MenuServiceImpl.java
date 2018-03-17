package pl.jakpoliczyc.dao.services.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.repos.MenuRepository;
import pl.jakpoliczyc.dao.services.MenuConsumer;
import pl.jakpoliczyc.dao.services.MenuService;
import pl.jakpoliczyc.dao.services.MenuTreeTraverser;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuTreeTraverser treeTraverser;

    @Override
    public void clearAndSaveMenu(List<Menu> menusToAdd) {
        final List<Menu> menus = menuRepository.findAll();

        menus.forEach(menu -> {
            deleteNode(menu);
            menuRepository.remove(menu);
        });

        menusToAdd.forEach(menuRepository::save);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Menu> find(long id) {
        return menuRepository.find(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Menu> findAll() {
        List<Menu> menus = menuRepository.findAll();
        treeTraverser.sortEachLevel(menus, Comparator.comparing(Menu::getName));
        return menus;
    }

    protected void deleteNode(final Menu menu) {

        final MenuConsumer menuConsumer = this::deleteNode;
        if (CollectionUtils.isNotEmpty(menu.getSubmenus())) {
            treeTraverser.childCallback(menu, menuConsumer);
        } else {
            if (deleteMenuItself(menu)) {
                deleteParent(menu);
                treeTraverser.siblingCallback(menu, menuConsumer);
            }
        }
    }

    protected boolean deleteMenuItself(final Menu menu) {
        menuRepository.remove(menu);
        if (menu.getParent() == null) {
            return false;
        }
        return menu.getParent().getSubmenus().remove(menu);
    }

    protected void deleteParent(final Menu menu) {
        if (CollectionUtils.isEmpty(menu.getParent().getSubmenus())) {
            deleteNode(menu.getParent());
        }
    }

}
