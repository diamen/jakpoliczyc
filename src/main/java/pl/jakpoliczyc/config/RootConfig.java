package pl.jakpoliczyc.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pl.jakpoliczyc.web.preparers.ArticlePreparer;

@Configuration
@ImportResource("classpath:*-db-config.xml")
@ComponentScan(basePackages = {"pl.jakpoliczyc"},
                excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
                })
public class RootConfig {

    @Bean
    public ArticlePreparer articlePreparer() {
        return new ArticlePreparer();
    }

}
