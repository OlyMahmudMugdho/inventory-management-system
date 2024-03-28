package com.OlyMahmudMugdho.inventorymanagementsystem.configs;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests((request) -> {
                    request
                            .requestMatchers("/resources/**", "/js/**", "/styles/**","/css/**").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                            .anyRequest().authenticated();

                })
                .formLogin( (form) -> {
                    form.loginPage("/login")
                            .defaultSuccessUrl("/")
                    .permitAll();
                } );

        return httpSecurity.build();
    }
}
