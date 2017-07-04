package pl.jakpoliczyc.dao.services;

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
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.entities.Story;
import pl.jakpoliczyc.dao.repos.MenuRepository;
import pl.jakpoliczyc.dao.repos.TagService;
import pl.jakpoliczyc.web.dto.ArticleCompressedDto;
import pl.jakpoliczyc.web.dto.CommentDto;
import pl.jakpoliczyc.web.dto.MenuDto;
import pl.jakpoliczyc.web.dto.StoryMenuTagDto;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static junitparams.JUnitParamsRunner.$;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("TEST")
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/fake.xml", type = DatabaseOperation.CLEAN_INSERT)
@DbUnitConfiguration(dataSetLoader = ArticleServiceTestIntegration.Loader.class)
public class ArticleServiceTestIntegration {

    @ClassRule
    public static final SpringClassRule SCR = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return ArticleServiceTestIntegration.getDataset();
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

                .newRow("articles").with("id", 2).with("added_date", new Date()).with("content", "content").with("intro", "intro").with("title", "title")
                .with("menu_id", 2).add()

                .newRow("tags").with("id", 1).with("name", "Exists").add()
                .newRow("tags").with("id", 2).with("name", "Also").add()

                .newRow("art_tag").with("art_id", 1).with("tag_id", 1).add()
                .newRow("art_tag").with("art_id", 1).with("tag_id", 2).add()

                .build();
    }

    @Autowired
    private ArticleService articleService;

    @Autowired
    @Qualifier("menuRepositoryStub")
    private MenuRepository menuRepository;

    @Autowired
    private TagService tagService;

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
    public void shouldListBeIncreasedOnlyByElementsWithIdEqualedToZero(List<MenuDto> param) throws Exception {
        // given
        int sizeBefore = menuRepository.findAll().size();
        int noOfElementsWithIdEqualedToZero = param.stream().filter(e -> e.getId() == 0).collect(Collectors.toList()).size();

        // when
        Method method = ((Advised) articleService).getTargetSource().getTarget().getClass().getDeclaredMethod("prepareMenu", new Class[]{List.class});
        method.setAccessible(true);
        Menu menu = (Menu) method.invoke(((Advised) articleService).getTargetSource().getTarget(), param);
        method.setAccessible(false);
        if (menu.getId() == 0) {
            menuRepository.save(menu);
        }

        // then
        int sizeAfter = menuRepository.findAll().size();
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

        int sizeBefore = menuRepository.findAll().size();

        // when
        articleService.save(storyMenuTagDto);

        // then
        int sizeAfter = menuRepository.findAll().size();
        assertThat(sizeAfter).isEqualTo(sizeBefore);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertNewArticleWithWholeNewMenu() {
        // given
        MenuDto menuDto = new MenuDto();
        menuDto.setId(0);
        menuDto.setName("I do not exist already");
        Story story = new Story();
        story.setTitle("any title");
        story.setContent("any content");
        story.setIntro("any intro");
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto();
        storyMenuTagDto.setMenus(Arrays.asList(menuDto));
        storyMenuTagDto.setStory(story);

        int sizeBefore = articleService.findAll(pageable).getContent().size();

        // when
        articleService.save(storyMenuTagDto);

        // then
        assertThat(sizeBefore + 1).isEqualTo(articleService.findAll(pageable).getContent().size());
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
        articleService.save(storyMenuTagDto);

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

        int sizeBefore = articleService.findAll(pageable).getContent().size();

        // when
        articleService.save(storyMenuTagDto);
        int sizeAfter = articleService.findAll(pageable).getContent().size();

        // then
        assertThat(sizeAfter).isGreaterThan(sizeBefore);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertedArticleHasReferenceToAlreadyExistingTag() {
        // given
        List<MenuDto> existingMenus = getShouldInsertToMenuWorkWithTestData3();
        Story story = new Story();
        story.setTitle("any title");
        story.setIntro("any intro");
        story.setContent("any content");
        List<String> tags = Arrays.asList("Exists");
        StoryMenuTagDto storyMenuTagDto = new StoryMenuTagDto(story, tags, existingMenus);

        // when
        articleService.save(storyMenuTagDto);

        // then
        ArticleCompressedDto currentlyAddedArticle = articleService.findAll(pageable).getContent()
                .get(articleService.findAll(pageable).getContent().size() - 1);
        assertThat(currentlyAddedArticle.getTags().size()).isEqualTo(tags.size());
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListOfArticlesDecreaseAfterRemove() {
        // given
        int sizeBefore = articleService.findAll(pageable).getContent().size();

        // when
        articleService.delete(1);

        // then
        int sizeAfter = articleService.findAll(pageable).getContent().size();
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListOfArticlesWithCommentsDecreaseAfterRemove() {
        // given
        List<ArticleCompressedDto> articles = articleService.findAll(pageable).getContent();
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("blabla");
        commentDto.setAuthor("author");
        articleService.save(1, commentDto);
        int sizeBefore = articleService.findAll(pageable).getContent().size();

        // when
        articleService.find(1);
        articleService.delete(1);

        // then
        int sizeAfter = articleService.findAll(pageable).getContent().size();
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldFindArticlesByTagId() {
        // given
        int expectedSize = 1;

        // when
        int returnedSize = articleService.findByTagId(1).size();

        // then
        assertThat(expectedSize).isEqualTo(returnedSize);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldFindArticlesByMenuId() {
        // given
        int expectedSize = 2;

        // when
        int returnedSize = articleService.findByMenuId(2).size();

        // then
        assertThat(expectedSize).isEqualTo(returnedSize);
    }

}