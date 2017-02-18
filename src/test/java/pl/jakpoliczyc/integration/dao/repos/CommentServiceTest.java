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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import pl.jakpoliczyc.dao.repos.CommentService;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/fake.xml")
@DbUnitConfiguration(dataSetLoader = CommentServiceTest.Loader.class)
public class CommentServiceTest {

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return CommentServiceTest.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()
                .newRow("comments").with("id", 1).with("author", "Jan Kowalski")
                .with("content", "Lorem ipsum...").with("added_date", "2016-03-03").add()
        .build();
    }

    @Autowired
    private CommentService commentService;

    @Test
    public void shouldListSizeDecreaseAfterRemove() {
        // given
        int sizeBefore = commentService.findAll().size();

        // when
        commentService.remove(1);
        int sizeAfter = commentService.findAll().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

}
