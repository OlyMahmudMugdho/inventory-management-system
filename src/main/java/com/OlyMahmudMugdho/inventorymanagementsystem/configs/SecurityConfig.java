package com.OlyMahmudMugdho.inventorymanagementsystem.configs;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.UserRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            System.out.println(user);
            if (user != null){
                return user;
            }
            throw new UsernameNotFoundException("User not found");
        };
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((request) -> {
                    request
                            .requestMatchers("/resources/**", "/js/**", "/styles/**","/css/**").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/register").permitAll()
                            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                            .anyRequest().authenticated();

                })
                .formLogin( (form) -> {
                    form.loginPage("/login")
                            .defaultSuccessUrl("/")
                    .permitAll();
                } )
                .logout(logout -> logout.logoutSuccessUrl("/login?logout"));
        ;

        return httpSecurity.build();
    }
}
