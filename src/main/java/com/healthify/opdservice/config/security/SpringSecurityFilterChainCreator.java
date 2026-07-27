package com.healthify.opdservice.config.security;

import com.healthify.opdservice.util.utils.SecurityUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SpringSecurityFilterChainCreator {

    @Autowired
    @Qualifier("userDataSource")
    private HikariDataSource userDataSource;


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
                        .requestMatchers("/createUser","/register","/error","/security/*","/hello").permitAll()
                        .anyRequest().authenticated()   // IMPORTANT: fallback rule
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()))// this means that the barrer token is provided by a resource already that we configured and now we are just validating jwt
                .oauth2Login(Customizer.withDefaults()).build(); // this means that the app supports jwt and also expects algo to verify jwt using jwtdecoderFilter.



    }


    /**
     * Provides a UserDetailsManager backed by the application's user database.
     *
     * During username/password authentication (before issuing a JWT),
     * Spring Security's AuthenticationManager delegates to a
     * DaoAuthenticationProvider, which uses this bean to load the user
     * and validate the supplied credentials.
     *
     * This bean is not used for validating JWTs on subsequent requests;
     * those are authenticated by the JwtDecoder.
     */
    @Primary
    @Bean
    public UserDetailsManager jdbcUserDetailsManager(){
        UserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(userDataSource);
        return jdbcUserDetailsManager;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

//    @Bean("authTokenDecoder")
//    public JwtDecoder jwtDecoder(@Value("${dev.jwt.secret}") String secret){
//
//        SecretKey key = (SecretKey) SecurityUtils.buildKeyFromSecret(secret);
//
//        JwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(key).build();
//
//        return jwtDecoder;
//    }
}
