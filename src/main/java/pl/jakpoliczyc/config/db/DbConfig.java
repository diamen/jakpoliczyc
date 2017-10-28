package pl.jakpoliczyc.config.db;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import pl.jakpoliczyc.config.Caches;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableCaching
@Import({DevDbConfig.class, TestDbConfig.class})
public class DbConfig {

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
