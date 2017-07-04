package pl.jakpoliczyc.dao.repos;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class ArticleRepositoryTestIntegration {

    @Autowired
    private ArticleRepository articleRepository;

    private final Pageable pageable = new Pageable() {
        @Override
        public int getPageNumber() {
            return 1;
        }

        @Override
        public int getPageSize() {
            return 99999;
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
        int sizeBefore = articleRepository.findAll(pageable).getContent().size();

        // when
        articleRepository.insertArticle(getTestData());
        int sizeAfter = articleRepository.findAll(pageable).getContent().size();

        // then
        assertThat(sizeBefore).isLessThan(sizeAfter);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldListSizeDecreaseAfterRemove() {
        // given
        articleRepository.insertArticle(getTestData());
        Page<Article> articles = articleRepository.findAll(pageable);
        int sizeBefore = articles.getContent().size();
        long articleId = articles.getContent().get(0).getId();

        // when
        articleRepository.removeArticle(articleId);
        int sizeAfter = articleRepository.findAll(pageable).getContent().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertOfArticleWithItsDependencies() {
        // given
        int sizeBefore = articleRepository.findAll(pageable).getContent().size();

        // when
        articleRepository.insertArticle(getInsertData());
        int sizeAfter = articleRepository.findAll(pageable).getContent().size();

        // then
        assertThat(sizeBefore).isLessThan(sizeAfter);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertNewCommentWhenCommentsAlreadyExist() {
        // given
        articleRepository.insertArticle(getTestData());
        Article article = articleRepository.findAll(pageable).getContent().get(0);
        int sizeBefore = article.getComments() != null ? article.getComments().size() : 0;
        Comment comment = new Comment();
        comment.setAuthor("arnold");
        comment.setContent("Siema, fajna strona");
        comment.setAddedDate(new Date());

        // when
        List<Article> articles = articleRepository.findAll(pageable).getContent();
        articleRepository.insertComment(articles.get(articles.size() - 1).getId(), comment);
        int sizeAfter = articleRepository.findAll(pageable).getContent().get(0).getComments().size();

        // then
        assertThat(sizeBefore + 1).isEqualTo(sizeAfter);
    }

    @Rollback
    @Transactional
    @Test
    public void shouldInsertNewCommentWhenCommentsDontExist() {
        // given
        long id = 1l;
        Article article = new Article();
        article.setId(id);
        article.setAddedDate(new Date());
        Story story = new Story();
        story.setTitle("blabla");
        story.setContent("blabla");
        story.setIntro("blabla");
        article.setStory(story);
        Menu menu = new Menu();
        menu.setName("Fizyka");
        article.setMenu(menu);
        articleRepository.insertArticle(article);

        Comment comment = new Comment();
        comment.setAddedDate(new Date());
        comment.setAuthor("arnold");
        comment.setContent("Siema, fajny artykuł");

        // when
        articleRepository.insertComment(id, comment);

        // then
        assertThat(1).isEqualTo(articleRepository.findAll(pageable).getContent().get(0).getComments().size());
    }

}
