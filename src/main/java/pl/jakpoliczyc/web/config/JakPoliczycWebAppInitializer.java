package pl.jakpoliczyc.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pl.jakpoliczyc.config.RootConfig;
import pl.jakpoliczyc.web.config.WebConfig;

public class JakPoliczycWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
