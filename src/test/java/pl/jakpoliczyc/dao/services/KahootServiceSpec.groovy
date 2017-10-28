package pl.jakpoliczyc.dao.services

import com.github.springtestdbunit.annotation.DbUnitConfiguration
import com.github.springtestdbunit.dataset.AbstractDataSetLoader
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.builder.DataSetBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import pl.jakpoliczyc.dao.JakPoliczycDbSpec
import pl.jakpoliczyc.dao.common.KahootDifficulties
import pl.jakpoliczyc.dao.entities.Kahoot
import spock.lang.Subject
import spock.lang.Title

@Title("""
Tests for KahootService class
which allows to test database
operations related with Kahoot entity
""")
@Subject(KahootService.class)

@DbUnitConfiguration(dataSetLoader = Loader.class)
class KahootServiceSpec extends JakPoliczycDbSpec {

    @Autowired
    KahootService kahootService

    static String sampleUrl = "http://wwww.google.pl"

    static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return new DataSetBuilder()

                    .newRow("kahoot").with("id", 1).with("kahootdifficulties", 1)
                    .with("title", "title1").with("url", sampleUrl).add()

                    .newRow("kahoot").with("id", 2).with("kahootdifficulties", 2)
                    .with("title", "title2").with("url", sampleUrl).add()

                    .build()
        }
    }

    def "Should service correctly remove an element"() {
        given: "Number of elements before delete"
        def sizeBefore = kahootService.findAll().size()

        when: "Object is deleted"
        kahootService.delete 1

        then: "The size of the list is decreased"
        kahootService.findAll().size() == sizeBefore - 1

        and: "the exisiting element should have different id than the deleted"
        kahootService.findAll().get(0).getId() == 2
    }

    def "Should service correctly insert new element"() {
        given: "Number of elements before replace"
        def sizeBefore = kahootService.findAll().size()

        and: "object to replace"
        Kahoot kahoot = createKahoot(3, "title3")

        when: "Object is inserted"
        kahootService.insert([kahoot])

        then: "The size of the list is increased"
        kahootService.findAll().size() == sizeBefore + 1
    }

    Kahoot createKahoot(long id = 1, String title = "title", KahootDifficulties diff = KahootDifficulties.VERY_DIFFICULT,
                        URL url = new URL(sampleUrl)) {
        Kahoot kahoot = new Kahoot();
        kahoot.setTitle title
        kahoot.setId id
        kahoot.setUrl url
        kahoot.setKahootDifficulties diff
        return kahoot
    }

}