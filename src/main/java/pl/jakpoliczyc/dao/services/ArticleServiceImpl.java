package pl.jakpoliczyc.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Comment;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.entities.Tag;
import pl.jakpoliczyc.dao.repos.ArticleRepository;
import pl.jakpoliczyc.dao.repos.CommentRepository;
import pl.jakpoliczyc.dao.repos.MenuService;
import pl.jakpoliczyc.dao.repos.TagService;
import pl.jakpoliczyc.web.dto.CommentDto;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Transactional
@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MenuService menuService;

    @Autowired
    private TagService tagService;

    @Override
    @Transactional
    public void save(StoryMenuTagDto wrapper) {
        Menu menu = prepareMenu(wrapper.getMenus());
        Article article = new Article();
        article.setStory(wrapper.getStory());
        article.setMenu(menu);
        article.setTags(wrapper.getTags() != null ? prepareTags(wrapper.getTags()) : null);
        article.setAddedDate(new Date());
        articleRepository.insertArticle(article);
    }

    @Override
    @Transactional
    public void update(long articleId, StoryMenuTagDto wrapper) {
        Article article = articleRepository.find(articleId);
        Menu menu = prepareMenu(wrapper.getMenus());
        article.setStory(wrapper.getStory());
        article.setMenu(menu);
        article.setTags(wrapper.getTags() != null ? prepareTags(wrapper.getTags()) : null);
        articleRepository.insertArticle(article);
    }

    @Override
    @Transactional
    public void save(long articleId, CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setAuthor(commentDto.getAuthor());
        comment.setContent(commentDto.getContent());
        comment.setAddedDate(new Date());
        articleRepository.insertComment(articleId, comment);
    }

    @Override
    @Transactional
    public void removeComment(long articleId, long commentId) {
        articleRepository.removeComment(articleId, commentId);
    }

    private List<Tag> prepareTags(List<String> names) {
        return names.stream().map(e -> {
            Optional<Tag> tagOptional = tagService.findByName(e);
            if (tagOptional.isPresent()) {
                return tagOptional.get();
            } else {
                Tag tag = new Tag();
                tag.setName(e);
                tagService.save(tag);
                return tag;
            }
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    private Menu prepareMenu(List<MenuDto> wrappers) {
        MenuDto lastNotZero = wrappers.stream().filter(e -> e.id > 0).max((e1, e2) -> wrappers.indexOf(e1) - wrappers.indexOf(e2)).get();
        List<MenuDto> menuToInsert = wrappers.stream().filter(e -> e.getId() == 0).collect(Collectors.toList());
        Menu lastExist = menuService.find(lastNotZero.getId());

        Queue<Menu> menuQueue = new LinkedBlockingQueue<>();

        if (menuToInsert.size() == 0) {
            return lastExist;
        }

        Menu firstNotExist = null;
        for (int i = menuToInsert.size() - 1; i >= 0; i--) {
            MenuDto currentMenu = menuToInsert.get(i);
            firstNotExist = new Menu();
            firstNotExist.setName(currentMenu.getName());
            if (menuQueue.size() > 0) {
                Menu previous = menuQueue.poll();
                previous.setParent(firstNotExist);
                firstNotExist.setSubmenus(Arrays.asList(previous));
            }
            menuQueue.add(firstNotExist);

            if (i == 0) {
                firstNotExist.setParent(lastExist);
            }
        }

        return firstNotExist;
    }

    @Override
    @Transactional(readOnly = true)
    public Article find(long id) {
        return articleRepository.find(id);
    }

    @Override
    @Transactional
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
