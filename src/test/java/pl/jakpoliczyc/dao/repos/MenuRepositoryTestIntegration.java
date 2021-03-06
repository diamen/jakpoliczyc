package pl.jakpoliczyc.dao.repos;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import pl.jakpoliczyc.dao.JakPoliczycDbTest;

import static org.assertj.core.api.Assertions.assertThat;

@DbUnitConfiguration(dataSetLoader = MenuRepositoryTestIntegration.Loader.class)
public class MenuRepositoryTestIntegration extends JakPoliczycDbTest {

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return MenuRepositoryTestIntegration.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()

                // DATA
                .newRow("menu").with("id", 1).with("name", "x").add()

                .newRow("menu").with("id", 2).with("name", "x").with("parent_id", 1).add()
                .newRow("menu").with("id", 11).with("name", "x").with("parent_id", 2).add()
                .newRow("menu").with("id", 12).with("name", "x").with("parent_id", 2).add()
                .newRow("menu").with("id", 13).with("name", "x").with("parent_id", 2).add()

                .newRow("menu").with("id", 3).with("name", "x").with("parent_id", 1).add()
                .newRow("menu").with("id", 14).with("name", "x").with("parent_id", 3).add()
                .newRow("menu").with("id", 15).with("name", "x").with("parent_id", 3).add()
                .newRow("menu").with("id", 16).with("name", "x").with("parent_id", 3).add()

                .newRow("menu").with("id", 4).with("name", "x").with("parent_id", 1).add()
                .newRow("menu").with("id", 17).with("name", "x").with("parent_id", 4).add()
                .newRow("menu").with("id", 18).with("name", "x").with("parent_id", 4).add()
                .newRow("menu").with("id", 19).with("name", "x").with("parent_id", 4).add()

                .newRow("menu").with("id", 5).with("name", "x").with("parent_id", 1).add()
                .newRow("menu").with("id", 6).with("name", "x").with("parent_id", 5).add()
                .newRow("menu").with("id", 7).with("name", "x").with("parent_id", 5).add()
                .newRow("menu").with("id", 8).with("name", "x").with("parent_id", 5).add()
                .newRow("menu").with("id", 9).with("name", "x").with("parent_id", 5).add()
                .newRow("menu").with("id", 20).with("name", "x").with("parent_id", 9).add()

                .newRow("menu").with("id", 10).with("name", "x").with("parent_id", 1).add()

                .build();
    }

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void shouldRemoveElementsWithParenId() {
        // given
        final int expectedCount = 1;

        // when
        int resultCount = menuRepository.findAll().size();

        // then
        assertThat(resultCount).isEqualTo(expectedCount);
    }

    @Test
    public void shouldNumberOfChildrenBeCorrect() {
        // given
        final int expectedCount = 5;

        // when
        int resultCount = menuRepository.findAll().get(0).getSubmenus().size();

        // then
        assertThat(resultCount).isEqualTo(expectedCount);
    }
}
