package pl.jakpoliczyc.dao.services.impl;

import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
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
import pl.jakpoliczyc.dao.services.MenuTreeTraverser;
import pl.jakpoliczyc.web.dto.ArticleCompressedDto;
import pl.jakpoliczyc.web.dto.CommentDto;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private MenuTreeTraverser treeTraverser;

    private UrlToStringConverter converter = new UrlToStringConverter();

    @Override
    public void save(StoryMenuTagDto wrapper) {
        Menu menu = prepareMenu(wrapper.getMenus());
        Article article = new Article();
        article.setStory(wrapper.getStory());
        article.setMenu(menu);
        article.setTags(wrapper.getTags() != null ? prepareTags(wrapper.getTags()) : null);
        article.setAddedDate(new Date());
        article.setUrl(converter.convertToEntityAttribute(wrapper.getYoutube()));
        article.setPdf(converter.convertToEntityAttribute(wrapper.getPdf()));
        article.setKahoot(wrapper.getKahoot());
        articleRepository.insertArticle(article);
    }

    @Override
    public void update(long articleId, StoryMenuTagDto wrapper) {
        Article article = articleRepository.find(articleId);
        final Menu menu = CollectionUtils.isEmpty(wrapper.getMenus()) ? article.getMenu() : prepareMenu(wrapper.getMenus());
        article.setStory(wrapper.getStory());
        article.setMenu(menu);
        article.setTags(wrapper.getTags() != null ? prepareTags(wrapper.getTags()) : null);
        article.setUrl(converter.convertToEntityAttribute(wrapper.getYoutube()));
        article.setPdf(converter.convertToEntityAttribute(wrapper.getPdf()));
        article.setKahoot(wrapper.getKahoot());
        articleRepository.insertArticle(article);
    }

    @Override
    public void delete(long articleId) {
        articleRepository.removeArticle(articleId);
    }

    @Override
    public void save(long articleId, CommentDto commentDto) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDto, comment);
        comment.setAddedDate(new Date());
        articleRepository.insertComment(articleId, comment);
    }

    @Override
    public void delete(long articleId, long commentId) {
        articleRepository.removeComment(articleId, commentId);
    }

    @Override
    @Transactional(readOnly = true)
    public Article find(long id) {
        return articleRepository.find(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArticleCompressedDto> findAll(final Pageable pageable) {
        final Page<Article> articles = articleRepository.findAll(pageable);
        return new PageImpl<>(convertToCompressedList(articles.getContent()), pageable, articles.getTotalElements());
    }

    @Override
    public Page<ArticleCompressedDto> findByMenuId(final Pageable pageable, final long menuId) {
        final Set<Long> menuIds = menuRepository.find(menuId).map(
                menu -> treeTraverser.findDescendants(menu).stream()
                        .map(Menu::getId)
                        .collect(Collectors.toSet())
        ).orElse(Sets.newHashSet());

        final Page<Article> articles = articleRepository.findByMenuId(pageable, menuIds);
        return new PageImpl<>(convertToCompressedList(articles.getContent()), pageable, articles.getTotalElements());
    }

    @Override
    public Page<ArticleCompressedDto> findByTagId(final Pageable pageable, final List<Long> ids) {
        final Page<Article> articles = articleRepository.findByTagId(pageable, ids);
        return new PageImpl<>(convertToCompressedList(articles.getContent()), pageable, articles.getTotalElements());
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

    private Menu prepareMenu(List<MenuDto> wrappers) {
        if (wrappers.get(0).getId() == 0) {
            return prepareMenuToInsert(wrappers, null);
        }
        final MenuDto lastNotZero = wrappers.stream().filter(e -> e.id > 0).max(Comparator.comparingInt(wrappers::indexOf)).get();
        final Menu lastExist = menuRepository.find(lastNotZero.getId()).get();

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

    private List<ArticleCompressedDto> convertToCompressedList(List<Article> articles) {
        return articles.stream().map(article ->
                new ArticleCompressedDto(article.getId(), article.getStory().getTitle(), article.getStory().getIntro(),
                        article.getAddedDate(), article.getTags(), new MenuDto(article.getMenu().getId(), article.getMenu().getName()))
        ).collect(Collectors.toList());
    }

}
