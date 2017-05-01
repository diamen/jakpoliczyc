package pl.jakpoliczyc.integration.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.entities.Story;
import pl.jakpoliczyc.dao.services.ArticleServiceImpl;
import pl.jakpoliczyc.integration.web.WebTestConfig;
import pl.jakpoliczyc.web.controllers.ArticleController;
import pl.jakpoliczyc.web.dto.CommentDto;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import javax.servlet.ServletContext;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ArticleControllerTest extends WebTestConfig {

    @Spy
    private ArticleServiceImpl articleService;

    @InjectMocks
    private ArticleController articleController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
    }

    @Test
    public void shouldControllerExists() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("articleController"));
    }

    @Test
    public void shouldReturnOkStatusWhenListIsNotEmpty() throws Exception {
        // given
        doReturn(Arrays.asList(new Article())).when(articleService).findAll();

        // when - then
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorNotFoundStatusWhenListIsEmpty() throws Exception {
        // given
        doReturn(Arrays.asList()).when(articleService).findAll();

        // when - then
        mockMvc.perform(get("/articles"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnErrorNotFoundStatusWhenListIsNull() throws Exception {
        // given
        doReturn(null).when(articleService).findAll();

        // when - then
        mockMvc.perform(get("/articles"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnOkStatusWhenRecordExists() throws Exception {
        // given
        doReturn(new Article()).when(articleService).find(1);

        // when - then
        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorNotFoundStatusWhenRecordDoesNotExist() throws Exception {
        // given
        doReturn(null).when(articleService).find(1);

        // when - then
        mockMvc.perform(get("/articles/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnCreatedStatusWhenSentArticleIsValidObject() throws Exception {
        // given
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto();
        storyMenuTagDto.setMenus(Arrays.asList(new MenuDto()));
        storyMenuTagDto.setStory(new Story());
        String requestJson = generateRequest(storyMenuTagDto);
        doNothing().when(articleService).save(anyObject());

        // when - then
        mockMvc.perform(post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnBadRequestWhenValidationOfArticleNotPass() throws Exception {
        // given
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto();
        storyMenuTagDto.setMenus(Arrays.asList(new MenuDto()));
        storyMenuTagDto.setStory(null);
        String requestJson = generateRequest(storyMenuTagDto);

        // when - then
        mockMvc.perform(post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenCommentIsEmpty() throws Exception {
        // given
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor("");
        commentDto.setContent("");
        String requestJson = generateRequest(commentDto);

        // when - then
        mockMvc.perform(post("/articles/1/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestWhenCommentIsNull() throws Exception {
        // given
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor(null);
        commentDto.setContent(null);
        String requestJson = generateRequest(commentDto);

        // when - then
        mockMvc.perform(post("/articles/1/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnCreatedStatusWhenSentCommentIsValidObject() throws Exception {
        // given
        CommentDto commentDto = new CommentDto();
        commentDto.setAuthor("arnold");
        commentDto.setContent("Siemka, fajna stronka");
        String requestJson = generateRequest(commentDto);
        doNothing().when(articleService).save(anyLong(), anyObject());

        // when - then
        mockMvc.perform(post("/articles/1/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnCompressedResult() throws Exception {
        // given
        Article article = new Article();
        article.setMenu(new Menu());
        Story story = new Story();
        story.setIntro("intro");
        story.setContent("content");
        story.setTitle("title");
        article.setStory(story);
        String requestJson = generateRequest(article);
        doReturn(Arrays.asList(article)).when(articleService).findAll();

        // when - then
        mockMvc.perform(get("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].story.title", is(story.getTitle())))
                .andExpect(jsonPath("$[0].story.intro", is(story.getIntro())))
                .andExpect(jsonPath("$[0].story.content").doesNotExist());
    }

}
