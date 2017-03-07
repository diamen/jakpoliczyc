package pl.jakpoliczyc.web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.managers.ArticleManager;
import pl.jakpoliczyc.dao.repos.ArticleService;
import pl.jakpoliczyc.web.common.View;
import pl.jakpoliczyc.web.wrappers.StoryMenuTagWrapper;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleManager articleManager;

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
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getArticle(@PathVariable long id) {
        Article article = articleService.find(id);
        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article with id %d not found", id));
        }
        return new ResponseEntity<>(articleService.find(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveArticle(@Valid @RequestBody StoryMenuTagWrapper storyMenuTagWrapper) {
        articleManager.save(storyMenuTagWrapper);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
