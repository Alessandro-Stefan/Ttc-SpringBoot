package com.ttc.app.config;

import com.ttc.app.security.properties.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ttc.app.security.filters.JwtAuthFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //TODO: To implement the methods
    private final JwtAuthFilter jwtAuthFilter;
    //TODO: To implement the methods
    private final UserDetailsService userDetailsService;
    private final SecurityProperties securityProps;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsService userDetailsService, SecurityProperties securityProps) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.securityProps = securityProps;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> {
                securityProps.getPublicEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(HttpMethod.valueOf(endpoint.getMethod()), endpoint.getPath()).permitAll();
                });

                securityProps.getAdminEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(HttpMethod.valueOf(endpoint.getMethod()), endpoint.getPath()).hasRole("ADMIN");
                });

                securityProps.getUserEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(HttpMethod.valueOf(endpoint.getMethod()), endpoint.getPath()).hasRole("USER");
                });})
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  configs) throws Exception {
        return configs.getAuthenticationManager();
    }
}
