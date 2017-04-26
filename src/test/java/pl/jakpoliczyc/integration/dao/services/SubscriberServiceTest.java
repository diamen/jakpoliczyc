package pl.jakpoliczyc.integration.dao.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.entities.Subscriber;
import pl.jakpoliczyc.dao.services.SubscriberService;

import javax.validation.ConstraintViolationException;

@ActiveProfiles("TEST")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "/fake.xml", type = DatabaseOperation.CLEAN_INSERT)
@DbUnitConfiguration(dataSetLoader = SubscriberServiceTest.Loader.class)
public class SubscriberServiceTest {

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return SubscriberServiceTest.getDataset();
        }
    }

    public static IDataSet getDataset() throws DataSetException {
        return new DataSetBuilder()

                .newRow("subscribers").with("email", "stopa23@interia.eu").add()
                .newRow("subscribers").with("email", "testowo@gmail.com").add()

                .build();
    }

    @Autowired
    private SubscriberService subscriberService;

    @Rollback
    @Transactional
    @Test
    public void shouldEmailBeInsertedCorrectly()  {
        // given
        Subscriber subscriber = new Subscriber("witam@wp.pl");
        int sizeBefore = subscriberService.findAll().size();

        // when
        boolean inserted = subscriberService.insertOrRemove(subscriber);

        // then
        int sizeAfter = subscriberService.findAll().size();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(inserted).isTrue();
        softly.assertThat(sizeBefore + 1).isEqualTo(sizeAfter);
        softly.assertAll();
    }

    @Rollback
    @Transactional
    @Test
    public void shouldEmailBeRemovedIfExists()  {
        // given
        Subscriber subscriber = new Subscriber("stopa23@interia.eu");
        int sizeBefore = subscriberService.findAll().size();

        // when
        boolean inserted = subscriberService.insertOrRemove(subscriber);

        // then
        int sizeAfter = subscriberService.findAll().size();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(inserted).isFalse();
        softly.assertThat(sizeBefore - 1).isEqualTo(sizeAfter);
        softly.assertAll();
    }

    @Rollback
    @Transactional
    @Test
    public void shouldValidatorNotAllowIncorrectEmails()  {
        // given - when - then
        Assertions.assertThatThrownBy(() -> {subscriberService.insertOrRemove(new Subscriber("lldfkl.eu")); })
                .isInstanceOf(ConstraintViolationException.class);
    }

}