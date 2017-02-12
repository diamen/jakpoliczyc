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

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/sql-data.xml")
public class CommentServiceTest {

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
