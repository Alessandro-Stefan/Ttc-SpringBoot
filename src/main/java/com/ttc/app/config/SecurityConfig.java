package com.ttc.app.config;

import com.ttc.app.security.properties.SecurityProperties;
import com.ttc.app.service.UserServiceImpl;
import com.ttc.app.util.JwtUtil;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ttc.app.security.filters.JwtAuthFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final SecurityProperties securityProps;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userService;

    public SecurityConfig(SecurityProperties securityProps, JwtUtil jwtUtil, UserServiceImpl userService) {
        this.securityProps = securityProps;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
        JwtAuthFilter jwtFilter = new JwtAuthFilter(jwtUtil, userService, securityProps);

        http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests(   
            auth -> {
                securityProps.getPublicEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(HttpMethod.valueOf(endpoint.getMethod()), endpoint.getPath()).permitAll();
                });

                securityProps.getUserEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(endpoint.getPath()).hasAnyRole("USER", "ADMIN");
                });
                
                securityProps.getAdminEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(endpoint.getPath()).hasRole("ADMIN");
                });

                auth.anyRequest().authenticated();
            })
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  configs) throws Exception {
        return configs.getAuthenticationManager();
    }
}
