package pl.jakpoliczyc.integration.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jakpoliczyc.dao.entities.Subscriber;
import pl.jakpoliczyc.dao.services.SubscriberService;
import pl.jakpoliczyc.integration.web.WebTestConfig;
import pl.jakpoliczyc.web.controllers.SubscriberController;

import javax.servlet.ServletContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SubscriberControllerTest extends WebTestConfig {

    @Mock
    private SubscriberService subscriberService;

    @InjectMocks
    private SubscriberController subscriberController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(subscriberController).build();
    }

    @Test
    public void shouldControllerExists() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("subscriberController"));
    }

    @Test
    public void shouldResourceBeCreatedWhenItDoesNotExist() throws Exception {
        // given
        Subscriber subscriber = new Subscriber("valid@gmail.com");

        // when
        when(subscriberService.insertOrRemove(any())).thenReturn(true);

        // then
        mockMvc.perform(post("/subscriber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateRequest(subscriber)))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldResourceBeOkWhenItDoesExist() throws Exception {
        // given
        Subscriber subscriber = new Subscriber("valid@gmail.com");

        // when
        when(subscriberService.insertOrRemove(any())).thenReturn(false);

        // then
        mockMvc.perform(post("/subscriber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateRequest(subscriber)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldValidationWork() throws Exception {
        // given
        Subscriber subscriber = new Subscriber("notvalid.com");

        // then
        mockMvc.perform(post("/subscriber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateRequest(subscriber)))
                .andExpect(status().isBadRequest());
    }

}
