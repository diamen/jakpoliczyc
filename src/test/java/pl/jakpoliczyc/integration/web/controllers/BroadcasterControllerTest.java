package pl.jakpoliczyc.integration.web.controllers;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jakpoliczyc.config.RootConfig;
import pl.jakpoliczyc.config.WebConfiguration;
import pl.jakpoliczyc.infrastructure.email.service.BroadcastService;
import pl.jakpoliczyc.integration.web.WebTestConfig;
import pl.jakpoliczyc.web.controllers.BroadcasterController;
import pl.jakpoliczyc.web.dto.EmailContentDto;

import javax.servlet.ServletContext;

import static junitparams.JUnitParamsRunner.$;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(classes = { WebConfiguration.class, RootConfig.class })
@ImportResource("classpath:test-db-config.xml")
@Configuration
@WebAppConfiguration
public class BroadcasterControllerTest extends WebTestConfig {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Mock
    private BroadcastService broadcastService;

    @InjectMocks
    private BroadcasterController broadcasterController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(broadcasterController).build();
    }

    @Test
    public void shouldControllerExists() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("broadcasterController"));
    }

    @Test
    public void shouldReturnCreatedWhenEmailWasBroadcasted() throws Exception {
        EmailContentDto emailContentDto = new EmailContentDto("title", "content");

        // when - then
        mockMvc.perform(post("/broadcaster")
            .content(generateRequest(emailContentDto))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    @Parameters
    public void shouldFailOnValidation(EmailContentDto dto) throws Exception {
        // given - when - then
        mockMvc.perform(post("/broadcaster")
            .content(generateRequest(dto))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    public Object[] parametersForShouldFailOnValidation() {
        return $(
                $(new EmailContentDto("title", null)),
                $(new EmailContentDto("", "")),
                $(new EmailContentDto("", "content")),
                $(new EmailContentDto(null, "rrorr")),
                $(new EmailContentDto(null, null)),
                $(new EmailContentDto("", ""))
        );
    }

}
