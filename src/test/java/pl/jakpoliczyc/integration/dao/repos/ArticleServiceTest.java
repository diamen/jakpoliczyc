package pl.jakpoliczyc.integration.dao.repos;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Comment;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.entities.Story;
import pl.jakpoliczyc.dao.repos.ArticleService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/sql-data.xml")
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    private Article getTestData() {
        Article article = new Article();
        article.setAddedDate(new Date());
        Story story = new Story();
        story.setIntro("Lorem ipsum");
        story.setContent("Lorem ipsum...");
        story.setTitle("LI");
        article.setStory(story);

        Menu parent = new Menu();
        parent.setName("Matematyka");
        Menu menu = new Menu();
        menu.setName("Funkcje");
        menu.setParent(parent);
        parent.setSubmenus(Arrays.asList(menu));
        article.setMenu(parent);

        return article;
    }

    private Article getInsertData() {
        Article article = new Article();
        article.setAddedDate(new Date());
        Story story = new Story();
        story.setIntro("Lorem ipsum");
        story.setContent("Lorem ipsum...");
        story.setTitle("LI");

        Menu menu = new Menu();
        menu.setName("Fizyka");

        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        comment1.setAddedDate(new Date());
        comment1.setAuthor("Annonymous");
        comment1.setContent("Lorem ipsum...");
        comment2.setAddedDate(new Date());
        comment2.setAuthor("Annonymous");
        comment2.setContent("Lorem ipsum...");

        article.setStory(story);
        article.setMenu(menu);
        article.setComments(Arrays.asList(comment1, comment2));

        return article;
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListSizeIncreaseAfterInsert() {
        // given
        int sizeBefore = articleService.findAll().size();

        // when
        articleService.insert(getTestData());
        int sizeAfter = articleService.findAll().size();

        // then
        assertThat(sizeBefore).isLessThan(sizeAfter);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListSizeDecreaseAfterRemove() {
        // given
        articleService.insert(getTestData());
        List<Article> articles = articleService.findAll();
        int sizeBefore = articles.size();
        long articleId = articles.get(0).getId();

        // when
        articleService.remove(articleId);
        int sizeAfter = articleService.findAll().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertOfArticleWithItsDependencies() {
        // given
        int sizeBefore = articleService.findAll().size();

        // when
        articleService.insert(getInsertData());
        int sizeAfter = articleService.findAll().size();

        // then
        assertThat(sizeBefore).isLessThan(sizeAfter);
    }

}
