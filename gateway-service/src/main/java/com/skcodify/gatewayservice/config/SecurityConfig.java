package com.skcodify.gatewayservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = Logger.getLogger(SecurityConfig.class.getName());

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((authorize) -> authorize
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails shekhar = User.builder()
                .username("shekhar")
                .password("$10$m/yjy8pMrZG3vnjJnA6XFeh2JR24/tKL5czDDwKiExG3KcErRnB.W")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("$10$z0USihCxeM/FuqdOSWaTAeBNVAQjYcT2rQC8qUKqxXbRiF4MrBIJK")
                .roles("ADMIN")
                .build();

        LOGGER.info("Admin: " + admin.getPassword());
        LOGGER.info("shekhar: " + shekhar.getPassword());

        return new InMemoryUserDetailsManager(shekhar, admin);
    }
}
