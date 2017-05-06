package pl.jakpoliczyc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.jakpoliczyc.dao.entities.User;
import pl.jakpoliczyc.dao.repos.UserService;

import java.util.stream.Collectors;

@Service
public class JakPoliczycUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JakPoliczycUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user != null) {
            return new JwtUser(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
                            .stream().map(e -> new SimpleGrantedAuthority(e.getAuthority())).collect(Collectors.toList())
            );
        }

        throw new UsernameNotFoundException(String.format("User %s not found", username));
    }

}
