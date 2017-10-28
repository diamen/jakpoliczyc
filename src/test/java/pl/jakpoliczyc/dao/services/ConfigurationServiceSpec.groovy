package pl.jakpoliczyc.dao.services

import com.github.springtestdbunit.annotation.DbUnitConfiguration
import com.github.springtestdbunit.dataset.AbstractDataSetLoader
import org.dbunit.dataset.IDataSet
import org.dbunit.dataset.builder.DataSetBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import pl.jakpoliczyc.dao.JakPoliczycDbSpec
import pl.jakpoliczyc.web.dto.ConfigurationDto
import spock.lang.Subject
import spock.lang.Title

@Title("""
Tests for ConfigurationService class
which allows to test database
operations related with Configuration entity
""")
@Subject(ConfigurationService.class)

@DbUnitConfiguration(dataSetLoader = Loader.class)
class ConfigurationServiceSpec extends JakPoliczycDbSpec {

    @Autowired
    ConfigurationService service

    static class Loader extends AbstractDataSetLoader {
        @Override
        protected IDataSet createDataSet(Resource resource) throws Exception {
            return new DataSetBuilder()

                    .newRow("configuration").with("keyy", "keyy1").with("value", "value1").add()
                    .newRow("configuration").with("keyy", "keyy2").with("value", "value2").add()

                    .build()
        }
    }

    def "Should current configuration be replaced by a new one"() {
        given: "Current configuration"
        def currentConfs = service.findAll()

        and: "configuration to insert"
        def newConfs = [createConf("keyy3", "value3"), createConf("keyy4", "value4")]

        when: "Configuration is replaced"
        service.replace newConfs

        then: "Previous and new configuration should not insersect"
        [] == currentConfs.intersect(newConfs)
    }

    ConfigurationDto createConf(String keyy, String value) {
        ConfigurationDto conf = new ConfigurationDto()
        conf.setKeyy(keyy)
        conf.setValue(value)
        return conf
    }

}