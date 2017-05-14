package pl.jakpoliczyc.web.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.entities.Story;
import pl.jakpoliczyc.dao.services.StorageService;
import pl.jakpoliczyc.web.IntegrationWebTestConfig;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StorageDto;

import javax.servlet.ServletContext;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StorageControllerTestIntegration extends IntegrationWebTestConfig {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private StorageController storageController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(storageController).build();
    }

    @Test
    public void shouldControllerExists() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(webApplicationContext.getBean("storageController"));
    }

    @Test
    public void shouldReturnOkStatusWhenListIsNotEmpty() throws Exception {
        // given
        doReturn(Arrays.asList(new Storage())).when(storageService).findAll();

        // when - then
        mockMvc.perform(get("/storage"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorNotFoundStatusWhenListIsEmpty() throws Exception {
        // given
        doReturn(Arrays.asList()).when(storageService).findAll();

        // when - then
        mockMvc.perform(get("/storage"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnErrorNotFoundStatusWhenListIsNull() throws Exception {
        // given
        doReturn(null).when(storageService).findAll();

        // when - then
        mockMvc.perform(get("/storage"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnOkStatusWhenRecordExists() throws Exception {
        // given
        doReturn(new Storage()).when(storageService).find(1);

        // when - then
        mockMvc.perform(get("/storage/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnErrorNotFoundStatusWhenRecordDoesNotExist() throws Exception {
        // given
        doThrow(new ResourceNotFoundException()).when(storageService).find(1);

        // when - then
        mockMvc.perform(get("/storage/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnCreatedStatusWhenStorageIsInserted() throws Exception {
        // given
        StorageDto storageDto = new StorageDto();
        storageDto.setStags(Arrays.asList("tag"));
        String requestJson = generateRequest(storageDto);
        doNothing().when(storageService).save(anyObject());

        // when - then
        mockMvc.perform(post("/storage")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnOkWhenStorageObjectIsUpdated() throws Exception {
        // given
        long id = 1;
        StorageDto storageDto = new StorageDto();
        Story story = new Story();
        story.setContent("content");
        story.setTitle("title");
        storageDto.setStags(Arrays.asList("tag"));
        String requestJson = generateRequest(storageDto);
        doNothing().when(storageService).update(anyLong(), anyObject());

        // when - then
        mockMvc.perform(put("/storage/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStorageBeCorrect() throws Exception {
        // given
        long id = 1;
        Storage storage = new Storage();
        Story story = new Story();
        story.setTitle("title");
        story.setIntro("intro");
        story.setContent("content");
        storage.setStory(story);
        doReturn(storage).when(storageService).find(id);

        // when - then
        mockMvc.perform(get("/storage/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.story.title", is(story.getTitle())))
                .andExpect(jsonPath("$.story.intro", is(story.getIntro())))
                .andExpect(jsonPath("$.story.content", is(story.getContent())));
    }

    @Test
    public void shouldReturnCompressedResult() throws Exception {
        Storage storage = new Storage();
        Story story = new Story();
        story.setTitle("title");
        story.setIntro("intro");
        story.setContent("content");
        storage.setStory(story);
        doReturn(Arrays.asList(storage)).when(storageService).findAll();

        mockMvc.perform(get("/storage/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].story.title", is(story.getTitle())))
                .andExpect(jsonPath("$[0].story.intro", is(story.getIntro())))
                .andExpect(jsonPath("$[0].story.content").doesNotExist());
    }

    @Test
    public void shouldReturnBadRequestWhenRequestIsNotValid() throws Exception {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(0);
        menuDto.setName("");

        mockMvc.perform(post("/storage/publish/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(generateRequest(menuDto)))
                .andExpect(status().isBadRequest());
    }

}
