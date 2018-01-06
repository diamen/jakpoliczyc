package pl.jakpoliczyc.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.jakpoliczyc.config.Profiles;
import pl.jakpoliczyc.dao.repos.utils.RepositoryUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Profile(Profiles.DEV)
@Configuration
public class DevDbConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        final EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();

        final Properties properties = new Properties();
        properties.setProperty("eclipselink.logging.level", "FINEST");
        properties.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
        properties.setProperty("javax.persistence.schema-generation.create-source", "metadata");
        properties.setProperty("javax.persistence.schema-generation.drop-source", "metadata");
        properties.setProperty("javax.persistence.sql-load-script-source", "classpath:sql/test-data.sql");

        final Map<String, String> jpaMapProperties = new HashMap<>();
        jpaMapProperties.put("eclipselink.weaving", "false");

        final LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setPersistenceUnitName(RepositoryUtils.PERSISTENCE_UNIT_NAME);
        lcemfb.setDataSource(dataSource);
        lcemfb.setPackagesToScan("pl.jakpoliczyc.dao");
        lcemfb.setJpaProperties(properties);
        lcemfb.setJpaPropertyMap(jpaMapProperties);
        lcemfb.setJpaVendorAdapter(adapter);

        return lcemfb;
    }

}
