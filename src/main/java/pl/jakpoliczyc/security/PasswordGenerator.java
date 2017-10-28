package pl.jakpoliczyc.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        final String encodedPassword = new BCryptPasswordEncoder().encode(args[0]);
        System.out.println(encodedPassword);
    }

}
