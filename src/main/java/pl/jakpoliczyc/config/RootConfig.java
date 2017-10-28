package pl.jakpoliczyc.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySources({
    @PropertySource("classpath:db.properties"),
    @PropertySource("classpath:security.properties"),
    @PropertySource("classpath:smtp.properties")
})
@ComponentScan(basePackages = {"pl.jakpoliczyc"},
                excludeFilters = {
                    @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
                })
public class RootConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
