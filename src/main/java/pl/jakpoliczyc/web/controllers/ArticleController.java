package pl.jakpoliczyc.web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.services.ArticleService;
import pl.jakpoliczyc.web.common.View;
import pl.jakpoliczyc.web.dto.CommentDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @JsonView(View.Compress.class)
    @ResponseBody
    @RequestMapping(value = "/articles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticles() {
        List<Article> articles = articleService.findAll();
        if (articles == null || articles.isEmpty()) {
            throw new ResourceNotFoundException("None article found");
        }
        return new ResponseEntity<>(articleService.findAll(), HttpStatus.OK);
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
        articleService.removeComment(articleId, commentId);
        return new ResponseEntity<Object>(null, HttpStatus.OK);
    }

}
