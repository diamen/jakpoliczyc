package pl.jakpoliczyc.integration.dao.repos;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Stag;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.entities.Story;
import pl.jakpoliczyc.dao.repos.StorageService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/fake.xml")
@DbUnitConfiguration(dataSetLoader = StorageServiceTest.Loader.class)
public class StorageServiceTest {

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return StorageServiceTest.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()
                .newRow("storages").add()
                .build();
    }

    @Autowired
    private StorageService storageService;

    private Storage getTestData() {
        Storage storage = new Storage();
        Story story = new Story();
        story.setContent("Lorep ipsum...");
        story.setIntro("Lorem...");
        story.setTitle("LI");
        storage.setStory(story);
        Collection<Stag> stags = Arrays.asList(getStag("TEST"), getStag("OK"));
        storage.setStags(stags);
        return storage;
    }

    private Stag getStag(String name) {
        Stag stag = new Stag();
        stag.setName(name);
        return stag;
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListSizeIncreaseAfterInsert() {
        // given
        int before = storageService.findAll().size();
        Storage storage = getTestData();

        // when
        storageService.insert(storage);
        int after = storageService.findAll().size();

        // then
        assertThat(after).isEqualTo(before + 1);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListSizeDecreaseAfterRemove() {
        // given
        Storage storage = getTestData();
        storageService.insert(storage);
        List<Storage> storages = storageService.findAll();
        int sizeBefore = storages.size();
        long id = storages.get(0).getId();

        // when
        storageService.remove(id);
        int sizeAfter = storageService.findAll().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

}
