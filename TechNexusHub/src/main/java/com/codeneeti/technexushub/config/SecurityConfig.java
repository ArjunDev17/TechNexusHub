package com.codeneeti.technexushub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails noramlUser = User.builder()
                .username("kabbu")
                .password(passwordEncoder().encode("123"))
                .roles("normal")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin12"))
                .roles("admin")
                .build();
//        InMemoryUserDetailsManager -is implimentation class of UsersDetailService
        return new InMemoryUserDetailsManager(noramlUser,admin);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
