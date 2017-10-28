package pl.jakpoliczyc.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.jakpoliczyc.config.Profiles;
import pl.jakpoliczyc.config.RootConfig;
import pl.jakpoliczyc.config.web.WebConfiguration;
import pl.jakpoliczyc.dao.IntegrationDbTestConfig;

@Ignore
@ActiveProfiles(Profiles.TEST)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, RootConfig.class})
@Configuration
@WebAppConfiguration
public class IntegrationWebTestConfig extends IntegrationDbTestConfig {

    public String generateRequest(Object object) throws JsonProcessingException {
        return WebTestUtils.generateRequest(object);
    }

}
