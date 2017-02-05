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
import pl.jakpoliczyc.dao.entities.Stag;
import pl.jakpoliczyc.dao.entities.Storage;
import pl.jakpoliczyc.dao.entities.Story;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("/sql-data.xml")
public class StorageServiceTest {

    @Autowired
    private StorageService storageService;

    private Storage getTestData() {
        Storage storage = new Storage();
        Story story = new Story();
        story.setContent("Lorep ipsum...");
        story.setIntro("Lorem...");
        story.setTitle("LI");
        storage.setStory(story);
        Collection<Stag> stags = Arrays.asList(getStag("TEST"), getStag("OK"));
        storage.setStags(stags);
        return storage;
    }

    private Stag getStag(String name) {
        Stag stag = new Stag();
        stag.setName(name);
        return stag;
    }

    @Test
    public void shouldListSizeIncreaseAfterInsert() {
        // given
        int before = storageService.findAll().size();
        Storage storage = getTestData();

        // when
        storageService.insert(storage);
        int after = storageService.findAll().size();

        // then
        assertThat(after).isEqualTo(before + 1);
    }

    @Test
    public void shouldListSizeDecreaseAfterRemove() {
        // given
        Storage storage = getTestData();
        storageService.insert(storage);
        List<Storage> storages = storageService.findAll();
        int sizeBefore = storages.size();
        long id = storages.get(0).getId();

        // when
        storageService.remove(id);
        int sizeAfter = storageService.findAll().size();

        // then
        assertThat(sizeBefore).isGreaterThan(sizeAfter);
    }

}
