package com.OlyMahmudMugdho.inventorymanagementsystem.configs;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.RoleRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.UserRepository;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            System.out.println(user.getAuthorities());
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
                            .requestMatchers("/resources/**", "/js/**", "/styles/**","/css/**","/fragments/**","/api/v1/**").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/register").permitAll()
                            .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                            //.requestMatchers("/").hasAuthority("ADMIN")
                            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                            .anyRequest().authenticated();

                })
                .formLogin( (form) -> {
                    form.loginPage("/login")
                            .defaultSuccessUrl("/?continue")
                    .permitAll();
                } )
                .logout(logout -> logout.logoutSuccessUrl("/login?logout"));
        ;

        return httpSecurity.build();
    }
}
