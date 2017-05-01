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
import pl.jakpoliczyc.dao.repos.StorageRepository;

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
@DbUnitConfiguration(dataSetLoader = StorageRepositoryTest.Loader.class)
public class StorageRepositoryTest {

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return StorageRepositoryTest.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()
                .newRow("storages").add()
                .build();
    }

    @Autowired
    private StorageRepository storageRepository;

    private Storage getTestData() {
        Storage storage = new Storage();
        Story story = new Story();
        story.setContent("Lorep ipsum...");
        story.setIntro("Lorem...");
        story.setTitle("LI");
        storage.setStory(story);
        return storage;
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListSizeIncreaseAfterInsert() {
        // given
        int before = storageRepository.findAll().size();
        Storage storage = getTestData();

        // when
        storageRepository.insert(storage);
        int after = storageRepository.findAll().size();

        // then
        assertThat(after).isEqualTo(before + 1);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListSizeDecreaseAfterRemove() {
        // given
        Storage storage = getTestData();
        storageRepository.insert(storage);
        List<Storage> storages = storageRepository.findAll();
        int sizeBefore = storages.size();
        long id = storages.get(0).getId();

        // when
        storageRepository.delete(id);
        int sizeAfter = storageRepository.findAll().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

}
