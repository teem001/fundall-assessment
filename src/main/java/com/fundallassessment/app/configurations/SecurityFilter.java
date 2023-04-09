package com.fundallassessment.app.configurations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.fundallassessment.app.enums.Role.ADMIN;
import static com.fundallassessment.app.enums.Role.SUPER_ADMIN;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
@Slf4j
public class SecurityFilter {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final String[] AUTH_WHITE_LIST = {
            "/api/assessment/user/login",
            "/api/assessment/user/register"
               };
    private final String[] ADMIN_ONLY = {
            "api/assessment/card","api/assessment/card/*","api/assessment/card/**"
    };
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Inside the security filter  ");
        http
                .cors()
                .and()
                .csrf()
                .disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITE_LIST)

                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(ADMIN_ONLY)
                .hasRole(SUPER_ADMIN.name())
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        log.warn("out of security filter");
        return http.build();
    }

//    @Bean    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers(AUTH_WHITE_LIST)
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
}
