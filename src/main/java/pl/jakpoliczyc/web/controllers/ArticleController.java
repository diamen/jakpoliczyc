package pl.jakpoliczyc.web.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.repos.ArticleService;
import pl.jakpoliczyc.web.common.View;
import pl.jakpoliczyc.web.wrappers.StoryMenuTag;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @JsonView(View.Compress.class)
    @ResponseBody
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public List<Article> getArticles() {
        return articleService.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public Article getArticle(@PathVariable long id) {
        return articleService.find(id);
    }

    @RequestMapping(value = "/articles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveArticle(@RequestBody StoryMenuTag storyMenuTag) {
        // TODO
        System.out.println(storyMenuTag);
    }
}
