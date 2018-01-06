package pl.jakpoliczyc.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.jakpoliczyc.config.Caches;
import pl.jakpoliczyc.config.Profiles;

import javax.persistence.EntityManagerFactory;
import java.util.function.UnaryOperator;

@Configuration
@EnableCaching
@EnableTransactionManagement
@ComponentScan("pl.jakpoliczyc.dao")
@Import({DevDbConfig.class, ProdDbConfig.class, TestDbConfig.class})
public class DbConfig {

    @Value("#{environment.getActiveProfiles()[0]}")
    private String activeProfile;

    @Autowired
    private Environment environment;

    private static final String propDriverClassName = "jdbc.driverClassName";
    private static final String propUrl = "jdbc.url";
    private static final String propUsername = "jdbc.username";
    private static final String propPassword = "jdbc.password";
    private final UnaryOperator<String> pattern = prop ->
            environment.getProperty(String.format("%s-%s", activeProfile, prop));

    @Profile({Profiles.DEV, Profiles.PROD})
    @Bean
    public DriverManagerDataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(pattern.apply(propDriverClassName));
        dataSource.setUrl(pattern.apply(propUrl));
        dataSource.setUsername(pattern.apply(propUsername));
        dataSource.setPassword(pattern.apply(propPassword));
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(Caches.ARTICLE_CACHE, Caches.MENU_CACHE);
    }

}
