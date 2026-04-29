package com.healthify.opdservice.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityFilterChainCreator {

    @Bean()
    public UserDetailsManager userDetailsManager(){

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));


        UserDetails userDetails = User.builder()
                .username("teja")
                .password("teja123")
                .authorities(authorityList)
                .build();


        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(userDetails);
        return userDetailsManager;

    }

    //use no encryption until this is learnt in depth, just to remove exe
    @Bean
    PasswordEncoder passwordEncoder(){
              return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())   // updated style
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/createUser","/register").permitAll()
                        .anyRequest().authenticated()   // IMPORTANT: fallback rule
                )
                .httpBasic(Customizer.withDefaults()).build(); // updated style


    }

}
