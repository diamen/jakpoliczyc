package pl.jakpoliczyc.integration.web.preparers;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.repos.MenuService;
import pl.jakpoliczyc.web.preparers.ArticlePreparer;
import pl.jakpoliczyc.web.wrappers.MenuWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("TEST")
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-config.xml", "classpath:test-beans.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/fake.xml")
@DbUnitConfiguration(dataSetLoader = pl.jakpoliczyc.integration.web.preparers.ArticlePreparerTest.Loader.class)
public class ArticlePreparerTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return pl.jakpoliczyc.integration.web.preparers.ArticlePreparerTest.getDataset();
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
    private ArticlePreparer preparer;

    @Autowired
    @Qualifier("menuServiceStub")
    private MenuService menuService;

    private List<MenuWrapper> getShouldInsertWorkTestData1() {
        return Arrays.asList(new MenuWrapper(1, "Podstawowe własności figur geometrycznych na płaszczyźnie"),
                new MenuWrapper(4, "Wielokąty wpisane w okrąg i opisane na okręgu"),
                new MenuWrapper(18, "Okrąg wpisany w czworokąt"),
                new MenuWrapper(0, "Nie istniejący dział"),
                new MenuWrapper(0, "Również nie istniejący dział"));
    }

    private List<MenuWrapper> getShouldInsertWorkTestData2() {
        return Arrays.asList(new MenuWrapper(1, "Podstawowe własności figur geometrycznych na płaszczyźnie"),
                new MenuWrapper(4, "Wielokąty wpisane w okrąg i opisane na okręgu"),
                new MenuWrapper(18, "Okrąg wpisany w czworokąt"),
                new MenuWrapper(0, "Nie istniejący dział"));
    }

    private List<MenuWrapper> getShouldInsertWorkTestData3() {
        return Arrays.asList(new MenuWrapper(1, "Podstawowe własności figur geometrycznych na płaszczyźnie"),
                new MenuWrapper(4, "Wielokąty wpisane w okrąg i opisane na okręgu"),
                new MenuWrapper(18, "Okrąg wpisany w czworokąt"));
    }

    private List<MenuWrapper> getShouldInsertWorkTestData4() {
        return Arrays.asList(new MenuWrapper(1, "Podstawowe własności figur geometrycznych na płaszczyźnie"));
    }

    @Test
    @Parameters
    public void shouldListBeIncreasedOnlyByElementsWithIdEqualedToZero(List<MenuWrapper> param) {
        // given
        int sizeBefore = menuService.findAll().size();
        int noOfElementsWithIdEqualedToZero = param.stream().filter(e -> e.getId() == 0).collect(Collectors.toList()).size();

        // when
        Menu menu = preparer.prepareMenu(param);
        if (menu.getId() == 0) {
            menuService.save(menu);
        }

        // then
        int sizeAfter = menuService.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore + noOfElementsWithIdEqualedToZero);
    }

    public Object[] parametersForShouldListBeIncreasedOnlyByElementsWithIdEqualedToZero() {
        return $(
          $(getShouldInsertWorkTestData1()),
          $(getShouldInsertWorkTestData2()),
          $(getShouldInsertWorkTestData3()),
          $(getShouldInsertWorkTestData4())
          );
    }
}