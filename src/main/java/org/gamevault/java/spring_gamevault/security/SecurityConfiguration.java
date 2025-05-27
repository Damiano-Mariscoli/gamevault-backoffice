package org.gamevault.java.spring_gamevault.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/api/**").permitAll()

                .anyRequest().authenticated()
            )
            .cors(cors -> {
                CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList("http://localhost:8080/api/*"));
                config.setAllowedMethods(Arrays.asList("GET"));
                config.setAllowedHeaders(Arrays.asList("*"));
                cors.configurationSource(source);
            })
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
            .exceptionHandling()
            .and().csrf().disable()
        ;
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
    UserDetailsService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}