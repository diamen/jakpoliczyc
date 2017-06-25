package pl.jakpoliczyc.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.mobile.device.Device
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@TestPropertySource(properties = [
        "jwt.header=Authorization",
        "jwt.secret=verySecret",
        "jwt.expiration=604800"
])
@ContextConfiguration(classes = [Config.class])




@Title("""
Tests for JwtTokenUtils class
and its decryption methods, which are necessary
to retrieve some important data from the token
""")
@Subject(JwtTokenUtils)
class JwtTokenUtilsSpec extends Specification {

    @Value('${jwt.expiration}')
    Long expiration;

    @Autowired
    JwtTokenUtils jwtTokenUtils

    def "Testing decryption of an username from a token"() {
        given: "username which should be returned from a token"
        final String expectedUsername = 'admin'

        and: "generated token"
        final String token = jwtTokenUtils.generateToken(getUserDetails(expectedUsername), Stub(Device))

        when: "username is retrieved from the token"
        final String givenUsername = jwtTokenUtils.getUsernameFromToken token

        then: "retrieved username is equal to expected username"
        givenUsername == expectedUsername
    }

    def "Testing decryption of authorities from a token"() {
        given: "role which should be returned from a token"
        final String expectedRole = "ROLE_ADMIN"

        and: "generated token"
        final String token = jwtTokenUtils.generateToken(getUserDetails("any", expectedRole), Stub(Device))

        when: "role is retrieved from the token"
        final String givenRole = jwtTokenUtils.getAuthoritiesFromToken token

        then: "retrieved role is equal to expected username"
        givenRole == expectedRole
    }

    def getUserDetails(final String username = "any", final String role = "any") {
        final UserDetails userDetails = Stub UserDetails
        final GrantedAuthority grantedAuthority = Stub GrantedAuthority
        grantedAuthority.getAuthority() >> role
        userDetails.getAuthorities() >> [grantedAuthority]
        userDetails.getPassword() >> "admin23"
        userDetails.getUsername() >> username
        userDetails.isAccountNonExpired() >> false
        userDetails.isAccountNonLocked() >> true
        userDetails.isCredentialsNonExpired() >> true
        userDetails.isEnabled() >> true
        return userDetails
    }

    @Configuration
    static class Config {
        @Bean
        static PropertySourcesPlaceholderConfigurer propertiesResolver() {
            return new PropertySourcesPlaceholderConfigurer()
        }

        @Bean
        static JwtTokenUtils jwtTokenUtilsBean() { return new JwtTokenUtils() }
    }

}