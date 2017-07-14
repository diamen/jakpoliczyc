package pl.jakpoliczyc.dao.services.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.converters.UrlToStringConverter;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Comment;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.entities.Tag;
import pl.jakpoliczyc.dao.repos.ArticleRepository;
import pl.jakpoliczyc.dao.repos.MenuRepository;
import pl.jakpoliczyc.dao.repos.TagService;
import pl.jakpoliczyc.dao.services.ArticleService;
import pl.jakpoliczyc.web.dto.ArticleCompressedDto;
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
    private MenuRepository menuRepository;

    @Autowired
    private TagService tagService;

    private UrlToStringConverter converter = new UrlToStringConverter();

    @Override
    @Transactional
    public void save(StoryMenuTagDto wrapper) {
        Menu menu = prepareMenu(wrapper.getMenus());
        Article article = new Article();
        article.setStory(wrapper.getStory());
        article.setMenu(menu);
        article.setTags(wrapper.getTags() != null ? prepareTags(wrapper.getTags()) : null);
        article.setAddedDate(new Date());
        article.setUrl(converter.convertToEntityAttribute(wrapper.getUrl()));
        articleRepository.insertArticle(article);
    }

    @Override
    @Transactional
    public void update(long articleId, StoryMenuTagDto wrapper) {
        cleanMenu();

        Article article = articleRepository.find(articleId);
        Menu menu = prepareMenu(wrapper.getMenus());
        article.setStory(wrapper.getStory());
        article.setMenu(menu);
        article.setTags(wrapper.getTags() != null ? prepareTags(wrapper.getTags()) : null);
        article.setUrl(converter.convertToEntityAttribute(wrapper.getUrl()));
        articleRepository.insertArticle(article);
    }

    @Transactional
    public void cleanMenu() {
        menuRepository.findAllUnparsed().stream().filter(e -> CollectionUtils.isEmpty(e.getSubmenus())).forEach(e -> {
            clearMenu(e);
        });
    }

    @Transactional
    private synchronized void clearMenu(Menu menu) {
        if (CollectionUtils.isEmpty(menu.getSubmenus()) && CollectionUtils.isEmpty(menu.getArticles())) {
            if (menu.getParent() != null) {
                for (int i = 0; i < menu.getParent().getSubmenus().size(); i++) {
                    if (menu.getParent().getSubmenus().get(i).equals(menu)) {
                        menu.getParent().getSubmenus().remove(i);
                    }
                }
            }

            menuRepository.remove(menu);
            if (menu.getParent() != null) {
                if (CollectionUtils.isEmpty(menu.getParent().getSubmenus())) {
                    menu.getParent().setSubmenus(null);
                    menuRepository.remove(menu.getParent());
                }
            }
        }
        if (menu.getParent() != null) {
            clearMenu(menu.getParent());
        }
    }

    @Override
    @Transactional
    public void delete(long articleId) {
        articleRepository.removeArticle(articleId);
        cleanMenu();
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
    public void delete(long articleId, long commentId) {
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
        if (wrappers.get(0).getId() == 0) {
            return prepareMenuToInsert(wrappers, null);
        }
        MenuDto lastNotZero = wrappers.stream().filter(e -> e.id > 0).max((e1, e2) -> wrappers.indexOf(e1) - wrappers.indexOf(e2)).get();
        Menu lastExist = menuRepository.find(lastNotZero.getId());

        List<MenuDto> menuToInsertDto = wrappers.stream().filter(e -> e.getId() == 0).collect(Collectors.toList());
        if (menuToInsertDto.size() == 0) {
            return lastExist;
        }

        return prepareMenuToInsert(menuToInsertDto, lastExist);
    }

    private Menu prepareMenuToInsert(List<MenuDto> dtos, Menu lastExist) {
        Queue<Menu> menuQueue = new LinkedBlockingQueue<>();
        List<Menu> menuToInsert = new ArrayList<>();
        Menu firstNotExist;
        for (int i = dtos.size() - 1; i >= 0; i--) {
            MenuDto currentMenu = dtos.get(i);
            firstNotExist = new Menu();
            firstNotExist.setName(currentMenu.getName());
            if (menuQueue.size() > 0) {
                Menu previous = menuQueue.poll();
                previous.setParent(firstNotExist);
                firstNotExist.setSubmenus(Arrays.asList(previous));
            }
            menuQueue.add(firstNotExist);

            if (i == 0 && lastExist != null) {
                firstNotExist.setParent(lastExist);
                List<Menu> submenus = lastExist.getSubmenus();
                submenus.add(firstNotExist);
                lastExist.setSubmenus(submenus);
            }
            menuToInsert.add(firstNotExist);
        }

        return menuToInsert.get(0);
    }

    @Override
    @Transactional(readOnly = true)
    public Article find(long id) {
        return articleRepository.find(id);
    }

    @Override
    @Transactional
    public Page<ArticleCompressedDto> findAll(final Pageable pageable) {
        final Page<Article> articles = articleRepository.findAll(pageable);
        return new PageImpl<>(convertToCompressedList(articles.getContent()), pageable, articles.getTotalElements());
    }

    @Override
    public Page<ArticleCompressedDto> findByMenuId(final Pageable pageable, final long menuId) {
        final Page<Article> articles = articleRepository.findByMenuId(pageable, menuId);
        return new PageImpl<>(convertToCompressedList(articles.getContent()), pageable, articles.getTotalElements());
    }

    @Override
    public Page<ArticleCompressedDto> findByTagId(final Pageable pageable, final List<Long> ids) {
        final Page<Article> articles = articleRepository.findByTagId(pageable, ids);
        return new PageImpl<>(convertToCompressedList(articles.getContent()), pageable, articles.getTotalElements());
    }

    private List<ArticleCompressedDto> convertToCompressedList(List<Article> articles) {
        return articles.stream().map(article -> {
            return new ArticleCompressedDto(article.getId(), article.getStory().getTitle(), article.getStory().getIntro(),
                    article.getAddedDate(), article.getTags(), new MenuDto(article.getMenu().getId(), article.getMenu().getName()));
        }).collect(Collectors.toList());
    }
}
