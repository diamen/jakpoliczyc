package pl.jakpoliczyc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import pl.jakpoliczyc.cache.CacheConfig;
import pl.jakpoliczyc.cache.Cached;
import pl.jakpoliczyc.config.auto.AutoParent;
import pl.jakpoliczyc.dao.entities.Configuration;
import pl.jakpoliczyc.dao.config.DaoConfig;
import pl.jakpoliczyc.service.config.ServiceConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.serviceXmlTest();
    }

    public void serviceAutoTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("auto-beans.xml");

        applicationContext.getBean(AutoParent.class).run();
        applicationContext.getBean(AutoParent.class).run();
        applicationContext.getBean(AutoParent.class).run();

        System.out.println("*************************");
    }

    public void serviceAnnotationTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ServiceConfig.class);

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.setActiveProfiles("prod");
        applicationContext.refresh();

        applicationContext.getBean(TestParent.class).run();
        applicationContext.getBean(TestParent.class).run();
        applicationContext.getBean(TestParent.class).run();
        System.out.println(applicationContext.getBean(Env.class).toString());
        System.out.println("*************************");
    }

    public void serviceXmlTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("service-beans.xml");

        applicationContext.getBean(TestParent.class).run();
        applicationContext.getBean(TestParent.class).run();
        applicationContext.getBean(TestParent.class).run();

//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        applicationContext.getBean(LazyThing.class);

        System.out.println("*************************");
    }

    public void cacheTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CacheConfig.class);
        Cached cached = applicationContext.getBean(Cached.class);

        cached.getSth(1);
        cached.getSth(1);
    }

    public void daoTest() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoConfig.class);

        EntityManagerFactory entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Configuration configuration = new Configuration();
        configuration.setKeyy("jak");
        configuration.setValue("jakpoliczyc");

        entityManager.persist(configuration);
        transaction.commit();
        entityManager.close();
    }

}
