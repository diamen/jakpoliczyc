package pl.jakpoliczyc.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {
        "jwt.header=Authorization",
        "jwt.secret=verySecret",
        "jwt.expiration=604800"
})
@ContextConfiguration(classes = { JwtTokenUtilsTestUnit.Config.class})
public class JwtTokenUtilsTestUnit {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Configuration
    static class Config {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        @Bean
        public static JwtTokenUtils jwtTokenUtilsBean() {
            return new JwtTokenUtils();
        }
    }

    @Test
    public void shouldDecryptUsernameFromToken() {
        // given
        final String expectedUsername = "admin";
        final String token = jwtTokenUtils.generateToken(getUserDetails(expectedUsername), getDevice());

        // when
        final String givenUsername = jwtTokenUtils.getUsernameFromToken(token);

        // then
        assertThat(givenUsername).isEqualTo(expectedUsername);
    }

    @Test
    public void shouldDecryptAuthoritiesFromToken() {
        // given
        final String expectedRole = "ROLE_ADMIN";
        final String token = jwtTokenUtils.generateToken(getUserDetails("any"), getDevice());

        // when
        final String givenRole = jwtTokenUtils.getAuthoritiesFromToken(token);

        // then
        assertThat(givenRole).isEqualTo(expectedRole);
    }

    private UserDetails getUserDetails(String username) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Arrays.asList(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "ROLE_ADMIN";
                    }
                });
            }

            @Override
            public String getPassword() {
                return "admin23";
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

    private Device getDevice() {
        return new Device() {
            @Override
            public boolean isNormal() {
                return true;
            }

            @Override
            public boolean isMobile() {
                return false;
            }

            @Override
            public boolean isTablet() {
                return false;
            }

            @Override
            public DevicePlatform getDevicePlatform() {
                return null;
            }
        };
    }
}
