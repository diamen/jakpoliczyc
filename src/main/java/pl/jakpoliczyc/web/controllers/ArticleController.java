package pl.jakpoliczyc.web.controllers;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.services.ArticleService;
import pl.jakpoliczyc.web.dto.ArticleCompressedDto;
import pl.jakpoliczyc.web.dto.CommentDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/articles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticles(final Pageable pageable) {
        Page<ArticleCompressedDto> articles = articleService.findAll(pageable);
        if (articles == null || CollectionUtils.isEmpty(articles.getContent())) {
            throw new ResourceNotFoundException("None article found");
        }
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/articles/{id:[0-9]*}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticle(@PathVariable long id) {
        Article article = articleService.find(id);
        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article with id %d not found", id));
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/articles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveArticle(@Valid @RequestBody StoryMenuTagDto storyMenuTagDto) {
        articleService.save(storyMenuTagDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/articles/{id:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteArticle(@PathVariable long id) {
        articleService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/articles/{id:[0-9]*}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateArticle(@PathVariable long id, @Valid @RequestBody StoryMenuTagDto storyMenuTagDto) {
        articleService.update(id, storyMenuTagDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/articles/{id:[0-9]*}/comment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveComment(@PathVariable long id, @Valid @RequestBody CommentDto commentDto) {
        articleService.save(id, commentDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    @RequestMapping(value = "/articles/{articleId:[0-9]*}/comment/{commentId:[0-9]*}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteArticle(@PathVariable long articleId, @PathVariable long commentId) {
        articleService.delete(articleId, commentId);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/articles/menu/{id:[0-9]*}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticlesByMenuId(final Pageable pageable, @PathVariable long id) {
        return new ResponseEntity<Object>(articleService.findByMenuId(pageable, id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/articles/tag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticlesByTagId(final Pageable pageable, @RequestParam("ids") final Long[] ids) {
        return new ResponseEntity<Object>(articleService.findByTagId(pageable, Arrays.asList(ids)), HttpStatus.OK);
    }

}
