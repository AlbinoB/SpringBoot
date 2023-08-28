package com.albino.hibernatedemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserDetailsManagerExt {
    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        
        UserDetails albino = User.builder()
                                .username("albino")
                                .password("{noop}albino")
                                .roles("ADMIN","TEACHER")
                                .build();

        UserDetails samuel = User.builder()
                                .username("samuel")
                                .password("{noop}samuel")
                                .roles("STUDENT")
                                .build();

        return new InMemoryUserDetailsManager(albino,samuel);

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.authorizeHttpRequests(configurer->
                    configurer
                        .requestMatchers(HttpMethod.GET, "/api/students").hasAnyRole("STUDENT","ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/students").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/students").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/students").hasRole("ADMIN")
                    );

        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.csrf(csrf->csrf.disable());

        return httpSecurity.build();
    }
}
