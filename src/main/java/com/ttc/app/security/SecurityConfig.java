package com.ttc.app.security;

import com.ttc.app.service.UserServiceImpl;
import com.ttc.app.util.JwtUtil;
import com.ttc.app.util.constants.EntityConstants;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ttc.app.util.filters.JwtAuthFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final PasswordEncoder passwordEncoder;
    private final SecurityProperties securityProps;
    private final JwtUtil jwtUtil;
    private final UserServiceImpl userService;

    public SecurityConfig(SecurityProperties securityProps, JwtUtil jwtUtil, UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.securityProps = securityProps;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
        JwtAuthFilter jwtFilter = new JwtAuthFilter(jwtUtil, userService, securityProps);

        http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests( 
            auth -> {
                auth.requestMatchers("/error").permitAll();

                securityProps.getPublicEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(HttpMethod.valueOf(endpoint.getMethod()), endpoint.getPath()).permitAll();
                });

                securityProps.getUserEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(endpoint.getPath()).hasAnyAuthority(String.valueOf(EntityConstants.USER_ROLE.getValue()),
                                                                        String.valueOf(EntityConstants.ADMIN_ROLE.getValue()));
                });
                
                securityProps.getAdminEndpoints().forEach(endpoint -> {
                    auth.requestMatchers(endpoint.getPath()).hasAuthority(String.valueOf(EntityConstants.ADMIN_ROLE.getValue()));
                });

                auth.anyRequest().authenticated();
            })
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  configs) throws Exception {
        return configs.getAuthenticationManager();
    }
}
