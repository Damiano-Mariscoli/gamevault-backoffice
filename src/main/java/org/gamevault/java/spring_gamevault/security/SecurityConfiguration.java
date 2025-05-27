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
            .requestMatchers("/games/create", "/games/edit/**", "/games/*/category").hasAnyAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/games/**").hasAnyAuthority("ADMIN")
            .requestMatchers("/categories/create").hasAnyAuthority("ADMIN")
            .requestMatchers("/categories/*").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/categories/*/**").hasAnyAuthority("ADMIN")
            .requestMatchers("/games", "/games/**").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/games/create", "/games/edit/**", "/categories/**").hasAuthority("ADMIN")
            .requestMatchers("/games/**").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/css/**").permitAll() // aggiunta della regola del CSS
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
            .logoutSuccessUrl("/login") // redirect to home after logout
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
