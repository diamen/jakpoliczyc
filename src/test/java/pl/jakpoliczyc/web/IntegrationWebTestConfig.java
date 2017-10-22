package pl.jakpoliczyc.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.jakpoliczyc.config.RootConfig;
import pl.jakpoliczyc.config.WebConfiguration;
import pl.jakpoliczyc.dao.IntegrationDbTestConfig;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebConfiguration.class, RootConfig.class })
@ImportResource("classpath:test-db-config.xml")
@Configuration
@WebAppConfiguration
public class IntegrationWebTestConfig extends IntegrationDbTestConfig {

    public String generateRequest(Object object) throws JsonProcessingException {
        return WebTestUtils.generateRequest(object);
    }

}
