package com.albino.hibernatedemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsManagerExt {
    
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        
        UserDetails albino = User.builder()
                                .username("albino")
                                .password("{noop}albino")
                                .roles("ADMIN")
                                .build();

        UserDetails samuel = User.builder()
                                .username("samuel")
                                .password("{noop}samuel")
                                .roles("STUDENT")
                                .build();

        return new InMemoryUserDetailsManager(albino,samuel);

    }
}
