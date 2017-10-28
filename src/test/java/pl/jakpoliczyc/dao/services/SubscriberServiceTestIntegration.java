package pl.jakpoliczyc.dao.services;

import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import pl.jakpoliczyc.dao.JakPoliczycDbTest;
import pl.jakpoliczyc.dao.entities.Subscriber;

import javax.validation.ConstraintViolationException;

@DbUnitConfiguration(dataSetLoader = SubscriberServiceTestIntegration.Loader.class)
public class SubscriberServiceTestIntegration extends JakPoliczycDbTest {

    public static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return SubscriberServiceTestIntegration.getDataset();
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