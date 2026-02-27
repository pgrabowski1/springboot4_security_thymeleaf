package com.example.springboot4_security_thymeleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

import java.util.List;

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    private static final String MAIN_PAGE = "/";
    private static final String LOGIN_URL = "/login";
    private static final String ACCESS_DENIED = "/403";
    private static final String ADMIN_PAGE = "/admin";

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(ADMIN_PAGE).hasRole(UserRole.ADMIN.getValue())
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage(ACCESS_DENIED))
                .formLogin((form) -> form
                        .loginPage(LOGIN_URL)
                        .permitAll()
                )
                .logout(h -> h
                        .logoutSuccessUrl(MAIN_PAGE)
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder encoder) {
        String password = encoder.encode("admin_password");
        UserDetails user = User.withUsername("admin").password(password).roles(UserRole.ADMIN.getValue()).build();

        String password2 = encoder.encode("password");
        UserDetails user2 = User.withUsername("user").password(password2).roles(UserRole.USER.getValue()).build();

        return new InMemoryUserDetailsManager(List.of(user, user2));
    }


    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

}