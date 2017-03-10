package pl.jakpoliczyc.integration.web.controllers;

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
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.repos.MenuServiceImpl;
import pl.jakpoliczyc.integration.web.WebTestConfig;
import pl.jakpoliczyc.web.controllers.MenuController;

import javax.servlet.ServletContext;
import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuControllerTest extends WebTestConfig {

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

}
