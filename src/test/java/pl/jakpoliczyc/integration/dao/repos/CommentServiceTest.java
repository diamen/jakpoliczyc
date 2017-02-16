package pl.jakpoliczyc.integration.dao.repos;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlWriter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import pl.jakpoliczyc.dao.repos.CommentService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/" + CommentServiceTest.datasetFileName)
public class CommentServiceTest {

    protected static final String datasetFileName = "comments-data.xml";
    private static File file;

    @BeforeClass
    public static void test() throws IOException, DataSetException {
        file = new File("src/test/resources/" + datasetFileName);
        file.createNewFile();
        try (
                FileOutputStream outputStream = new FileOutputStream(file, false);
                ){
            new FlatXmlWriter(outputStream).write(getDataset());
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

    @AfterClass
    public static void afterClass() {
        file.delete();
    }

}
