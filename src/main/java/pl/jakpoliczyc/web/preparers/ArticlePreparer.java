package pl.jakpoliczyc.web.preparers;

import org.springframework.beans.factory.annotation.Autowired;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.entities.Tag;
import pl.jakpoliczyc.dao.repos.MenuService;
import pl.jakpoliczyc.web.wrappers.MenuWrapper;
import pl.jakpoliczyc.web.wrappers.StoryMenuTagWrapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class ArticlePreparer {

    @Autowired
    private MenuService menuService;

    public Article prepare(StoryMenuTagWrapper wrapper) {
        Menu menu = prepareMenu(wrapper.getMenus());
        List<Tag> tags = prepareTags(wrapper.getTags());
        Article article = new Article();
        article.setStory(wrapper.getStory());
        article.setMenu(menu);
        article.setTags(tags);
        article.setAddedDate(new Date());
        return article;
    }

    public List<Tag> prepareTags(List<String> tags) {
        return tags.stream().map(e -> {
            Tag tag = new Tag();
            tag.setName(e);
            return tag;
        }).collect(Collectors.toList());
    }

    public Menu prepareMenu(List<MenuWrapper> wrappers) {
        MenuWrapper lastNotZero = wrappers.stream().filter(e -> e.id > 0).max((e1, e2) -> wrappers.indexOf(e1) - wrappers.indexOf(e2)).get();
        List<MenuWrapper> menuToInsert = wrappers.stream().filter(e -> e.getId() == 0).collect(Collectors.toList());
        Menu lastExist = menuService.find(lastNotZero.getId());

        Queue<Menu> menuQueue = new LinkedBlockingQueue<>();

        if (menuToInsert.size() == 0) {
            return lastExist;
        }

        Menu firstNotExist = null;
        for (int i = menuToInsert.size() - 1; i >= 0; i--) {
            MenuWrapper currentMenu = menuToInsert.get(i);
            firstNotExist = new Menu();
            firstNotExist.setName(currentMenu.getName());
            if (menuQueue.size() > 0) {
                Menu menu3 = menuQueue.poll();
                menu3.setParent(firstNotExist);
                firstNotExist.setSubmenus(Arrays.asList(menu3));
            }
            menuQueue.add(firstNotExist);

            if (i == 0) {
                firstNotExist.setParent(lastExist);
            }
        }

        return firstNotExist;
    }
}
