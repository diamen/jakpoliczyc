package pl.jakpoliczyc.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pl.jakpoliczyc.*;

@Configuration
public class ServiceConfig {

    @Bean
    public TestParent testParent() {
        return new TestParent(testChild());
    }

    @Bean
    public TestChild testChild() {
        return new TestChild();
    }

    @Bean
    @Profile("dev")
    public Env devEnv() {
        return new Dev();
    }

    @Bean
    @Profile("prod")
    public Env prodEnv() {
        return new Prod();
    }

}
