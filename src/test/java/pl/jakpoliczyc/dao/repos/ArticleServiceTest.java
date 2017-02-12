package pl.jakpoliczyc.dao.repos;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import pl.jakpoliczyc.dao.entities.Article;
import pl.jakpoliczyc.dao.entities.Menu;
import pl.jakpoliczyc.dao.entities.Story;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/sql-data.xml")
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
        article.setMenu(menu);

        return article;
    }

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

}
