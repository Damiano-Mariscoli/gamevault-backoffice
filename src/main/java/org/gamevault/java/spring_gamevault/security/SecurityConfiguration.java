package org.gamevault.java.spring_gamevault.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
@SuppressWarnings("removal")
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/pizzas/create", "/pizzas/edit/**", "/pizzas/*/offerta").hasAnyAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/pizzas/**").hasAnyAuthority("ADMIN")
            .requestMatchers("/ingredienti/create").hasAnyAuthority("ADMIN")
            .requestMatchers("/ingredienti/*").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/ingredienti/*/**").hasAnyAuthority("ADMIN")
            .requestMatchers("/offerte/**").hasAnyAuthority("ADMIN")
            .requestMatchers("/pizzas", "/pizzas/**").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/games/create", "/games/edit/**", "/categories/**").hasAuthority("ADMIN")
            .requestMatchers("/games/**").hasAnyAuthority("USER", "ADMIN")
            .anyRequest().authenticated() // require authentication for all requests
        )
        .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/games", false) // redirect to login page immediately
            .permitAll()
        )
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/") // redirect to home after logout
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .permitAll()
        )
        .exceptionHandling();
    return http.build();
}

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
