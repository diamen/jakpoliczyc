package pl.jakpoliczyc.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.jakpoliczyc.dao.repos.UserService;
import pl.jakpoliczyc.security.JakPoliczycUserDetailsService;
import pl.jakpoliczyc.security.JwtAuthenticationTokenFilter;

@Configuration
public class SecurityBeanConfigurer {

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new JakPoliczycUserDetailsService(userService);
    }

}
