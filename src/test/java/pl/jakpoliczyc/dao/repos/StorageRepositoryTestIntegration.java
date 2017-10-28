package pl.jakpoliczyc.dao.repos;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.JakPoliczycDbTest;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.entities.Story;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DbUnitConfiguration(dataSetLoader = StorageRepositoryTestIntegration.Loader.class)
public class StorageRepositoryTestIntegration extends JakPoliczycDbTest {

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return StorageRepositoryTestIntegration.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()
                .newRow("storages").add()
                .build();
    }

    @Autowired
    private StorageRepository storageRepository;

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
        int before = storageRepository.findAll(pageable).getContent().size();
        Storage storage = getTestData();

        // when
        storageRepository.insert(storage);
        int after = storageRepository.findAll(pageable).getContent().size();

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
        List<Storage> storages = storageRepository.findAll(pageable).getContent();
        int sizeBefore = storages.size();
        long id = storages.get(0).getId();

        // when
        storageRepository.delete(id);
        int sizeAfter = storageRepository.findAll(pageable).getContent().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

}
