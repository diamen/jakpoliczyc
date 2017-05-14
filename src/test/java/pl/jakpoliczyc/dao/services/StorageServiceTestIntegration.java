package pl.jakpoliczyc.dao.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.api.SoftAssertions;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.entities.Story;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StorageDto;

import java.util.Arrays;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("TEST")
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/fake.xml", type = DatabaseOperation.CLEAN_INSERT)
@DbUnitConfiguration(dataSetLoader = StorageServiceTestIntegration.Loader.class)
public class StorageServiceTestIntegration {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return StorageServiceTestIntegration.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()

                .newRow("storages").with("id", 1).with("content", "content").with("intro", "intro").with("title", "title").add()

                .newRow("stags").with("id", 1).with("name", "Exists").add()
                .newRow("stags").with("id", 2).with("name", "Also").add()

                .newRow("sto_sta").with("sto_id", 1).with("sta_id", 1).add()
                .newRow("sto_sta").with("sto_id", 1).with("sta_id", 2).add()

                .build();
    }

    @Autowired
    private StorageService storageService;

    @Autowired
    private ArticleService articleService;

    public Object[] parametersForShouldInsertCorrectlyStorageObject() {
        StorageDto storage1 = new StorageDto();
        Story story1 = new Story();
        story1.setTitle("title");
        story1.setContent("content");
        story1.setIntro("intro");
        storage1.setStags(Arrays.asList("ok"));
        storage1.setStory(story1);

        StorageDto storage2 = new StorageDto();
        Story story2 = new Story();
        story2.setContent("content");
        storage2.setStory(story2);
        storage2.setStags(Arrays.asList("name"));

        return $(
                $(new StorageDto()),
                $(storage1),
                $(storage2)
        );
    }

    @Rollback
    @Transactional
    @Parameters
    @Test
    public void shouldInsertCorrectlyStorageObject(StorageDto wrapper) throws Exception {
        // given
        int sizeBefore = storageService.findAll().size();

        // when
        storageService.save(wrapper);

        // then
        assertThat(sizeBefore + 1).isEqualTo(storageService.findAll().size());
    }

    @Rollback
    @Transactional
    @Test
    public void shouldRemoveCorrectlyStorageObject() throws Exception {
        // given
        int sizeBefore = storageService.findAll().size();

        // when
        storageService.delete(1);

        // then
        assertThat(sizeBefore - 1).isEqualTo(storageService.findAll().size());
    }

    @Rollback
    @Transactional
    @Test
    public void shouldUpdateCorrectlyStorageObject() throws Exception {
        // given
        long id = 1;
        Storage storageBefore = storageService.find(id);
        StorageDto update = new StorageDto();
        Story story = new Story();
        final String contentAfter = "new content";
        story.setContent(contentAfter);
        update.setStory(story);

        // when
        storageService.update(1, update);

        // then
        Storage storageAfter = storageService.find(id);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(storageAfter.getStory().getContent()).isEqualTo(contentAfter);
        softly.assertThat(storageAfter.getStory().getTitle()).isNull();
        softly.assertThat(storageAfter.getStory().getIntro()).isNull();
        softly.assertAll();
    }

    @Rollback
    @Transactional
    @Test
    public void shouldPublishArticleFromStorage() throws Exception {
        // given
        long id = 1;
        int sizeOfStoragesBefore = storageService.findAll().size();
        int sizeOfArticlesBefore = articleService.findAll().size();
        MenuDto menuDto = new MenuDto();
        menuDto.setId(0);
        menuDto.setName("Funkcje");

        // when
        storageService.publish(id, Arrays.asList(menuDto));

        // then
        int sizeOfStoragesAfter = storageService.findAll().size();
        int sizeOfArticlesAfter = articleService.findAll().size();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(sizeOfStoragesBefore - 1).isEqualTo(sizeOfStoragesAfter);
        softly.assertThat(sizeOfArticlesBefore + 1).isEqualTo(sizeOfArticlesAfter);
        softly.assertAll();
    }

}