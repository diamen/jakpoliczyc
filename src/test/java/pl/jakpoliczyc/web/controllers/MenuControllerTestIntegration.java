package pl.jakpoliczyc.web.controllers;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.services.impl.MenuServiceImpl;
import pl.jakpoliczyc.web.IntegrationWebTestConfig;

import javax.servlet.ServletContext;
import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTestIntegration extends IntegrationWebTestConfig {

    @Spy
    private MenuServiceImpl menuService;

    @InjectMocks
    private MenuController menuController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(menuController).build();
    }

    @Test
    public void shouldControllerExists() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("menuController"));
    }

    @Test
    public void shouldReturnOkStatusWhenListIsNotEmpty() throws Exception {
        // given
        doReturn(Arrays.asList(new Menu())).when(menuService).findAll();

        // when - then
        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorNotFoundStatusWhenListIsEmpty() throws Exception {
        // given
        doReturn(Arrays.asList()).when(menuService).findAll();

        // when - then
        mockMvc.perform(get("/menu"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnErrorNotFoundStatusWhenListIsNull() throws Exception {
        // given
        doReturn(null).when(menuService).findAll();

        // when - then
        mockMvc.perform(get("/menu"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldArticleBeIgnoredInResponse() throws Exception {
        // given
        doReturn(Lists.newArrayList(createMenuMock())).when(menuService).findAll();

        // when - then
        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].articles").doesNotExist());
    }

    @Test
    public void shouldArticleBePresentInResponse() throws Exception {
        // given
        doReturn(Lists.newArrayList(createMenuMock())).when(menuService).findAll();

        // when - then
        mockMvc.perform(get("/menuWithArticle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].articles").exists());
    }

    protected Menu createMenuMock() {
        final Menu menu = new Menu();
        menu.setId(1L);
        menu.setName("name");
        final Menu submenu = new Menu();
        submenu.setId(2L);
        submenu.setParent(menu);
        submenu.setName("submenu");
        menu.setSubmenus(Lists.newArrayList(submenu));
        final Article article = new Article();
        article.setMenu(menu);
        article.setId(1L);
        menu.setArticles(Lists.newArrayList(article));

        return menu;
    }

}
