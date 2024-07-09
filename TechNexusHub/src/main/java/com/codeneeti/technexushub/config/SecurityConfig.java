package com.codeneeti.technexushub.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(
//                request -> {
//                    //this is authenticated
//                    request.requestMatchers("/hello").authenticated();
//                    //if url start from users then it is permit all
//                    request.requestMatchers("/users/**").permitAll();
////                    we can authorized particular method also
//                    request.requestMatchers(HttpMethod.DELETE,"/products").authenticated();
////                    request.requestMatchers(HttpMethod.DELETE,"/products").hasRole("ADMIN");
//                    request.anyRequest().permitAll();
//                }
//        );
        httpSecurity.authorizeHttpRequests(r -> r.requestMatchers("/hello").authenticated().
                requestMatchers("/users/**").permitAll().
                requestMatchers(HttpMethod.DELETE, "/products").authenticated().anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails noramlUser = User.withDefaultPasswordEncoder()
//                .username("kabbu")
//                .password(passwordEncoder().encode("123"))
//                .roles("normal")
//                .build();
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin12"))
//                .roles("admin")
//                .build();
////        InMemoryUserDetailsManager -is implimentation class of UsersDetailService
//        return new InMemoryUserDetailsManager(noramlUser, admin);
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf()
//                .disable()
//                        .cors()
//                                .disable()
//                                        .
//                authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//
//        return http.build();
//    }

//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    http.csrf(csrfConfigurer -> csrfConfigurer.disable())
//            .cors(corsConfigurer -> corsConfigurer.disable())
//            .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
//            .httpBasic(httpBasicConfigurer -> {
//                // Custom entry point for logging failed authentication
//                httpBasicConfigurer.authenticationEntryPoint((request, response, authException) -> {
//                    System.out.println("Authentication failed: " + authException.getMessage());
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                });
//            });
//
////    http.logout(logoutConfigurer -> logoutConfigurer.logoutSuccessHandler(logoutSuccessHandler()));
//
//    return http.build();
//}


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
