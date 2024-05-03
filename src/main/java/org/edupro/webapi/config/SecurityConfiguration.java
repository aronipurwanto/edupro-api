package org.edupro.webapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final KeycloakLogoutHandler keycloakLogoutHandler;

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                // Allows preflight requests from browser
                .requestMatchers(new AntPathRequestMatcher("/customers*", HttpMethod.OPTIONS.name()))
                .permitAll()
                .requestMatchers(new AntPathRequestMatcher("/customers*","/users*"))
                .hasRole("user")
                .requestMatchers(new AntPathRequestMatcher("/"))
                .permitAll()
                .anyRequest()
                .authenticated());
        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults()));
        http.oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout.addLogoutHandler(keycloakLogoutHandler)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
}