package pl.jakpoliczyc.dao.repos;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.JakPoliczycDbTest;

import static org.assertj.core.api.Assertions.assertThat;

@DbUnitConfiguration(dataSetLoader = CommentRepositoryTestIntegration.Loader.class)
public class CommentRepositoryTestIntegration extends JakPoliczycDbTest {

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return CommentRepositoryTestIntegration.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()
                .newRow("comments").with("id", 1).with("author", "Jan Kowalski")
                .with("content", "Lorem ipsum...").with("added_date", "2016-03-03").add()
                .build();
    }

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    @Test
    public void shouldListSizeDecreaseAfterRemove() {
        // given
        int sizeBefore = commentRepository.findAll().size();

        // when
        commentRepository.remove(1);
        int sizeAfter = commentRepository.findAll().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

}
