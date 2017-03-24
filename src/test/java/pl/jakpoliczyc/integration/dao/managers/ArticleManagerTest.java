package pl.jakpoliczyc.integration.dao.managers;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.entities.Story;
import pl.jakpoliczyc.dao.managers.ArticleManager;
import pl.jakpoliczyc.dao.repos.ArticleService;
import pl.jakpoliczyc.dao.repos.MenuService;
import pl.jakpoliczyc.dao.repos.TagService;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import java.util.Arrays;
import java.util.Date;
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
@DatabaseSetup(value = "/fake.xml", type = DatabaseOperation.CLEAN_INSERT)
@DbUnitConfiguration(dataSetLoader = ArticleManagerTest.Loader.class)
public class ArticleManagerTest {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return ArticleManagerTest.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()

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

                .newRow("articles").with("id", 1).with("added_date", new Date()).with("content", "content").with("intro", "intro").with("title", "title")
                .with("menu_id", 2).add()

                .newRow("tags").with("id", 1).with("name", "Exists").add()
                .newRow("tags").with("id", 2).with("name", "Also").add()

                .newRow("art_tag").with("art_id", 1).with("tag_id", 1).add()
                .newRow("art_tag").with("art_id", 1).with("tag_id", 2).add()

                .build();
    }

    @Autowired
    private ArticleManager articleManager;

    @Autowired
    @Qualifier("menuServiceStub")
    private MenuService menuService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    private List<MenuDto> getShouldInsertToMenuWorkWithTestData1() {
        return Arrays.asList(new MenuDto(1, "Podstawowe własności figur geometrycznych na płaszczyźnie"),
                new MenuDto(4, "Wielokąty wpisane w okrąg i opisane na okręgu"),
                new MenuDto(18, "Okrąg wpisany w czworokąt"),
                new MenuDto(0, "Nie istniejący dział"),
                new MenuDto(0, "Również nie istniejący dział"));
    }

    private List<MenuDto> getShouldInsertToMenuWorkWithTestData2() {
        return Arrays.asList(new MenuDto(1, "Podstawowe własności figur geometrycznych na płaszczyźnie"),
                new MenuDto(4, "Wielokąty wpisane w okrąg i opisane na okręgu"),
                new MenuDto(18, "Okrąg wpisany w czworokąt"),
                new MenuDto(0, "Nie istniejący dział"));
    }

    private List<MenuDto> getShouldInsertToMenuWorkWithTestData3() {
        return Arrays.asList(new MenuDto(1, "Podstawowe własności figur geometrycznych na płaszczyźnie"),
                new MenuDto(4, "Wielokąty wpisane w okrąg i opisane na okręgu"),
                new MenuDto(18, "Okrąg wpisany w czworokąt"));
    }

    private List<MenuDto> getShouldInsertToMenuWorkWithTestData4() {
        return Arrays.asList(new MenuDto(1, "Podstawowe własności figur geometrycznych na płaszczyźnie"));
    }

    @Rollback
    @Transactional
    @Test
    @Parameters
    public void shouldListBeIncreasedOnlyByElementsWithIdEqualedToZero(List<MenuDto> param) {
        // given
        int sizeBefore = menuService.findAll().size();
        int noOfElementsWithIdEqualedToZero = param.stream().filter(e -> e.getId() == 0).collect(Collectors.toList()).size();

        // when
        Menu menu = articleManager.prepareMenu(param);
        if (menu.getId() == 0) {
            menuService.save(menu);
        }

        // then
        int sizeAfter = menuService.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore + noOfElementsWithIdEqualedToZero);
    }

    public Object[] parametersForShouldListBeIncreasedOnlyByElementsWithIdEqualedToZero() {
        return $(
          $(getShouldInsertToMenuWorkWithTestData1()),
          $(getShouldInsertToMenuWorkWithTestData2()),
          $(getShouldInsertToMenuWorkWithTestData3()),
          $(getShouldInsertToMenuWorkWithTestData4())
          );
    }

    @Rollback
    @Transactional
    @Test
    public void shouldNotInsertPresentMenu() {
        // given
        List<MenuDto> existingMenus = getShouldInsertToMenuWorkWithTestData3();
        Story story = new Story();
        story.setTitle("any title");
        story.setIntro("any intro");
        story.setContent("any content");
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto(story, Arrays.asList("Not"), existingMenus);

        int sizeBefore = menuService.findAll().size();

        // when
        articleManager.save(storyMenuTagDto);

        // then
        int sizeAfter = menuService.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertOnlyNotPresentTags() {
        // given
        List<MenuDto> existingMenus = getShouldInsertToMenuWorkWithTestData3();
        Story story = new Story();
        story.setTitle("any title");
        story.setIntro("any intro");
        story.setContent("any content");
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto(story, Arrays.asList("Not", "Exists"), existingMenus);

        int tagsBefore = tagService.findAll().size();

        // when
        articleManager.save(storyMenuTagDto);

        // then
        int sizeAfter = tagService.findAll().size();
        assertThat(sizeAfter).isEqualTo(tagsBefore + 1);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertCorrectlyWhenTagsAreNull() {
        // given
        List<MenuDto> existingMenus = getShouldInsertToMenuWorkWithTestData3();
        Story story = new Story();
        story.setTitle("any title");
        story.setIntro("any intro");
        story.setContent("any content");
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto(story, null, existingMenus);

        int sizeBefore = articleService.findAll().size();

        // when
        articleManager.save(storyMenuTagDto);
        int sizeAfter = articleService.findAll().size();

        // then
        assertThat(sizeAfter).isGreaterThan(sizeBefore);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertedArticleHasReferenceToAlreadyExistedTag() {
        // given
        List<MenuDto> existingMenus = getShouldInsertToMenuWorkWithTestData3();
        Story story = new Story();
        story.setTitle("any title");
        story.setIntro("any intro");
        story.setContent("any content");
        List<String> tags = Arrays.asList("Exists");
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto(story, tags, existingMenus);

        // when
        articleManager.save(storyMenuTagDto);

        // then
        Article currentlyAddedArticle = articleService.find(articleService.findAll().size());
        assertThat(currentlyAddedArticle.getTags().size()).isEqualTo(tags.size());
    }

}