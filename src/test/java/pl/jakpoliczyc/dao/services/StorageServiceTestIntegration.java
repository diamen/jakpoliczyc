package pl.jakpoliczyc.dao.services;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import com.google.common.collect.Lists;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.JakPoliczycDbTest;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.entities.Story;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StorageDto;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
@DbUnitConfiguration(dataSetLoader = StorageServiceTestIntegration.Loader.class)
public class StorageServiceTestIntegration extends JakPoliczycDbTest {

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

    private final Pageable pageable = new Pageable() {
        @Override
        public int getPageNumber() {
            return 1;
        }

        @Override
        public int getPageSize() {
            return 9999;
        }

        @Override
        public int getOffset() {
            return 0;
        }

        @Override
        public Sort getSort() {
            return null;
        }

        @Override
        public Pageable next() {
            return null;
        }

        @Override
        public Pageable previousOrFirst() {
            return null;
        }

        @Override
        public Pageable first() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }
    };

    public Object[] parametersForShouldInsertCorrectlyStorageObject() {
        StorageDto storage1 = new StorageDto();
        Story story1 = new Story();
        story1.setTitle("title");
        story1.setContent("content");
        story1.setIntro("intro");
        storage1.setStags(Lists.newArrayList("ok"));
        storage1.setStory(story1);

        StorageDto storage2 = new StorageDto();
        Story story2 = new Story();
        story2.setContent("content");
        storage2.setStory(story2);
        storage2.setStags(Lists.newArrayList("name"));

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
        int sizeBefore = storageService.findAll(pageable).getContent().size();

        // when
        storageService.save(wrapper);

        // then
        assertThat(sizeBefore + 1).isEqualTo(storageService.findAll(pageable).getContent().size());
    }

    @Rollback
    @Transactional
    @Test
    public void shouldRemoveCorrectlyStorageObject() throws Exception {
        // given
        int sizeBefore = storageService.findAll(pageable).getContent().size();

        // when
        storageService.delete(1);

        // then
        assertThat(sizeBefore - 1).isEqualTo(storageService.findAll(pageable).getContent().size());
    }

    @Rollback
    @Transactional
    @Test
    public void shouldUpdateCorrectlyStorageObject() throws Exception {
        // given
        long id = 1;
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
        int sizeOfStoragesBefore = storageService.findAll(pageable).getContent().size();
        int sizeOfArticlesBefore = articleService.findAll(pageable).getContent().size();
        MenuDto menuDto = new MenuDto();
        menuDto.setId(0);
        menuDto.setName("Funkcje");

        // when
        storageService.publish(id, Lists.newArrayList(menuDto));

        // then
        int sizeOfStoragesAfter = storageService.findAll(pageable).getContent().size();
        int sizeOfArticlesAfter = articleService.findAll(pageable).getContent().size();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(sizeOfStoragesBefore - 1).isEqualTo(sizeOfStoragesAfter);
        softly.assertThat(sizeOfArticlesBefore + 1).isEqualTo(sizeOfArticlesAfter);
        softly.assertAll();
    }

}